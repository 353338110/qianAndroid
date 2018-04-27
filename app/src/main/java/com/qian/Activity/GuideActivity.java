package com.qian.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.qian.Base.BaseActivity;
import com.qian.Bean.User;
import com.qian.R;
import com.qian.RXjaveRetrofitUtil.RetrofitHelper;

import java.util.List;

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
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setSteepStatusBar(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtils.isGranted(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)){
                //系统弹窗权限已经申请了
                autoLogin();
            }else {
                //系统弹窗权限未通过
                PermissionUtils.permission(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> list) {
                        autoLogin();
                    }

                    @Override
                    public void onDenied(List<String> list, List<String> list1) {
                        ToastUtils.showLong("你居然敢拒绝！不给你看了！！！");
                    }
                }).request();
            }
        }else {
            autoLogin();
        }



    }

    private void autoLogin(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Login();
            }
        }, 2000);
    }

    private void Login(){
        if ("".equals(SPUtils.getInstance().getString("username"))){
            Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            RetrofitHelper.getInstance().login(new Subscriber<User>() {
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
                    Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPUtils.getInstance().getString("username"), SPUtils.getInstance().getString("password"));
        }

    }



}
