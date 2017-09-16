package com.qian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.qian.base.BaseActivity;
import com.qian.base.BasePresenter;
import com.qian.bean.User;
import com.qian.R;
import com.qian.utils.rxJaveRetrofitUtil.RetrofitHelper;
import com.qian.utils.Constants;

import butterknife.BindView;
import rx.Subscriber;


/**
 * Created by SHCai on 2017/8/13.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_guide;
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
        setSteepStatusBar(true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Login();
            }
        }, 2000);


    }

    private void Login(){
        if ("".equals(SPUtils.getInstance().getString("phone"))){
            Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            RetrofitHelper.getInstance().loginByPhone(new Subscriber<User>() {
                @Override
                public void onCompleted() {
                    LogUtils.w("onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.w("onError");
                    Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onNext(User user) {
                    LogUtils.w("onNext = " + user.toString());
                    Constants.user = user;
                    Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPUtils.getInstance().getString("phone"), SPUtils.getInstance().getString("password"));
        }

    }



}
