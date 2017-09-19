package com.qian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qian.R;
import com.qian.activity.MainActivity;
import com.qian.activity.RicheditorActivity;
import com.qian.activity.UploadActivity;
import com.qian.base.BaseFragment;
import com.qian.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by master on 2017/9/16.
 */

public class MoodLogFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_upload)
    TextView tvUpload;



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


    @OnClick(R.id.tv_upload)
    public void onViewClicked() {
        Intent intent = new Intent(mActivity, UploadActivity.class);
        //Intent intent = new Intent(mActivity, RicheditorActivity.class);
        startActivity(intent);
    }
}
