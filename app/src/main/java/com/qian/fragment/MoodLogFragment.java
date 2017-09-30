package com.qian.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qian.R;
import com.qian.base.BaseFragment;
import com.qian.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by master on 2017/9/16.
 */

public class MoodLogFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rcv_moodlog)
    RecyclerView rcvMoodlog;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mood_log;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        toolbarTitle.setText("MoodLog");
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }



}
