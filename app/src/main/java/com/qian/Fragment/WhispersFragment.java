package com.qian.Fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.qian.Base.BaseFragment;
import com.qian.Bean.Kaiyan.Daily;
import com.qian.R;
import com.qian.RXjaveRetrofitUtil.KRetrofitHelper;
import com.qian.RXjaveRetrofitUtil.ProgressSubscriber;
import com.qian.RXjaveRetrofitUtil.SubscriberOnNextListener;

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
    protected void initData() {

    }
}
