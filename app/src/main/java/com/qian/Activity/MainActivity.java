package com.qian.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.qian.Base.BaseActivity;
import com.qian.Bean.Kaiyan.Daily;
import com.qian.CustomView.LVGhost;
import com.qian.R;
import com.qian.RXjaveRetrofitUtil.KRetrofitHelper;
import com.qian.RXjaveRetrofitUtil.ProgressSubscriber;
import com.qian.RXjaveRetrofitUtil.RetrofitHelper;
import com.qian.RXjaveRetrofitUtil.SubscriberOnNextListener;
import com.qian.Utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @BindView(R.id.text)
    TextView text;


    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
                KRetrofitHelper.getInstance().getDaily(new ProgressSubscriber<Daily>(new SubscriberOnNextListener<Daily>() {
                    @Override
                    public void onNext(Daily daily) {
                        LogUtils.w(daily.toString());
                    }
                }));
                break;
    }
    }
}
