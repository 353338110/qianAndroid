package com.qian.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qian.R;
import com.qian.adapter.ReplyAdapter;
import com.qian.base.BaseActivity;
import com.qian.bean.kaiyan.ItemList;
import com.qian.bean.kaiyan.Replies;
import com.qian.bean.kaiyan.ReplyList;
import com.qian.contract.IReplyContract;
import com.qian.presenter.ReplyPresenter;
import com.qian.utils.CircleTransform;
import com.qian.utils.KTimeUtils;
import com.qian.utils.widget.FabToggle;
import com.qian.utils.widget.InsetDividerDecoration;
import com.qian.utils.widget.ParallaxScrimageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MovieDetailActivity extends BaseActivity<ReplyPresenter> implements IReplyContract.View,View.OnClickListener {


    @BindView(R.id.background)
    View background;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.backdrop)
    ParallaxScrimageView backdrop;
    @BindView(R.id.fab_play)
    FabToggle fabPlay;
    @BindView(R.id.content_layout)
    FrameLayout contentLayout;

    ReplyAdapter replyAdapter;
    @BindView(R.id.recycler_replies)
    RecyclerView recyclerReplies;

    private List<ReplyList> datas = new ArrayList<>();
    private ItemList item;
    private View movieDescription;
    private int lastId;
    private int fabOffset;
    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.movie_detail_activity;
    }

    @Override
    protected ReplyPresenter loadPresenter() {
        return new ReplyPresenter();
    }

    @Override
    public void initView(View view) {
        item = getIntent().getParcelableExtra("item");
    }

    @Override
    public void doBusiness(Context mContext) {

        mPresenter.reply();

        movieDescription = LayoutInflater.from(this)
                .inflate(R.layout.item_movie_detail_header, recyclerReplies, false);
        final TextView title = (TextView) movieDescription.findViewById(R.id.movie_title);
        final TextView type = (TextView) movieDescription.findViewById(R.id.movie_type);
        final TextView description = (TextView) movieDescription.findViewById(R.id.movie_desc);
        final ImageView author = (ImageView) movieDescription.findViewById(R.id.author);
        final TextView name = (TextView) movieDescription.findViewById(R.id.name);
        final LinearLayout authorContent = (LinearLayout) movieDescription.findViewById(R.id.author_content);

        title.setText(item.data.title);
        type.setText(item.data.category + " | " + KTimeUtils.secToTime((int) item.data.duration));
        description.setText(item.data.description);

        if (item.data.author != null) {
            authorContent.setVisibility(View.VISIBLE);
            name.setText(item.data.author.name);
            Glide.with(this)
                    .load(item.data.author.icon)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform(this)))
                    .into(author);
        }
        Glide.with(this)
                .load(item.data.cover.detail)
                .into(backdrop);
        recyclerReplies.addItemDecoration(new InsetDividerDecoration(
                ReplyAdapter.Holder.class,
                getResources().getDimensionPixelSize(R.dimen.divider_height),
                getResources().getDimensionPixelSize(R.dimen.keyline_1),
                ContextCompat.getColor(this, R.color.divider)
        ));
        replyAdapter = new ReplyAdapter(datas, movieDescription);
        fabPlay.setOnClickListener(this);
        recyclerReplies.setAdapter(replyAdapter);
        backdrop.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                backdrop.getViewTreeObserver().removeOnPreDrawListener(this);
                fabOffset = backdrop.getHeight() - fabPlay.getHeight() / 2 + title.getMeasuredHeight();
                fabPlay.setOffset(fabOffset);
                return true;
            }
        });

        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerReplies.getLayoutManager();

        recyclerReplies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                backdrop.setOffset(movieDescription.getTop());
                fabPlay.setOffset(fabOffset + movieDescription.getTop());
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (layoutManager.findFirstVisibleItemPosition() != 0
                        && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (lastId != 0) {
                        mPresenter.replyMore();
                    };
                }
            }
        });

    }


    @Override
    public int getId() {
        LogUtils.w("item.data.id = "+item.data.id);
        return item.data.id;
    }

    @Override
    public int getLastId() {
        LogUtils.w("lastId = "+lastId);
        return lastId;
    }

    @Override
    public void getReply(Replies replies) {
        datas.addAll(replies.replyList);
        lastId = replies.replyList.get(replies.replyList.size() - 1).sequence;
        replyAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMoreReply(Replies replies) {
        lastId = replies.replyList.get(replies.replyList.size() - 1).sequence;
        datas.addAll(replies.replyList);
        replyAdapter.notifyDataSetChanged();

    }
    @Override
    public void onBackPressed() {
        fabPlay.animate()
                .scaleX(0)
                .scaleY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        finishAfterTransition();
                    }
                })
                .start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finishAfterTransition();
                break;
            case R.id.fab_play:
                LogUtils.w("fab_play");
                Intent intent = new Intent(MovieDetailActivity.this, PlayActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
                break;
        }
    }
}
