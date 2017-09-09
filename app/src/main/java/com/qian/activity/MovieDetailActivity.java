package com.qian.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.qian.R;
import com.qian.adapter.ReplyAdapter;
import com.qian.base.BaseActivity;
import com.qian.base.BasePresenter;
import com.qian.bean.kaiyan.ItemList;
import com.qian.bean.kaiyan.ReplyList;
import com.qian.contract.IReplyContract;
import com.qian.utils.widget.FabToggle;
import com.qian.utils.widget.ParallaxScrimageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MovieDetailActivity extends BaseActivity implements IReplyContract.View{


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

    private List<ReplyList> datas = new ArrayList<>();
    private ItemList item;
    private View movieDescription;
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
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getLastId() {
        return 0;
    }
}
