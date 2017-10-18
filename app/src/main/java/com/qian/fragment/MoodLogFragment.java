package com.qian.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.qian.R;
import com.qian.adapter.QueryMoodAdapter;
import com.qian.base.BaseFragment;
import com.qian.bean.MoodLog;
import com.qian.bean.Pager;
import com.qian.contract.IMoodContract;
import com.qian.presenter.MoodLogPresenter;
import com.qian.utils.fragmentUtil.LazyFragmentPagerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by master on 2017/9/16.
 */

public class MoodLogFragment extends BaseFragment<MoodLogPresenter> implements LazyFragmentPagerAdapter.Laziable, IMoodContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rcv_moodlog)
    RecyclerView rcvMoodlog;

    QueryMoodAdapter queryMoodAdapter;

    int currentPager = 1;

    List<MoodLog> logList;
    @BindView(R.id.sfl_mood)
    SwipeRefreshLayout sflMood;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mood_log;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        toolbarTitle.setText("MoodLog");
    }

    @Override
    protected MoodLogPresenter loadPresenter() {
        return new MoodLogPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.queryMood();

        queryMoodAdapter = new QueryMoodAdapter(mActivity,R.layout.item_query_mood,logList);
        rcvMoodlog.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        rcvMoodlog.setAdapter(queryMoodAdapter);
    }

    @Override
    protected void initEvent() {
        sflMood.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPager = 1;
                mPresenter.queryMood();
            }
        });
    }


    @Override
    public int getCurrentPager() {
        return currentPager;
    }

    @Override
    public void getMoodLog(Pager<MoodLog> pager) {
        if (pager.isIsFirstPage()) {
            this.logList = pager.getList();
        } else {
            this.logList.addAll(pager.getList());
        }
        sflMood.setRefreshing(false);
        currentPager = pager.getNextPage();
        queryMoodAdapter.addData(logList);
        queryMoodAdapter.notifyDataSetChanged();
    }

}
