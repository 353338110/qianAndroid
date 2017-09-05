package com.qian.fragment;

import android.os.Bundle;
import android.view.View;

import com.qian.base.BaseFragment;
import com.qian.R;
import com.qian.base.BasePresenter;

/**
 * Created by master on 2017/8/25.
 */

public class WhispersFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_whispers;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

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
