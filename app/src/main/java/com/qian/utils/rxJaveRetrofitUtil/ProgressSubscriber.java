package com.qian.utils.rxJaveRetrofitUtil;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.qian.MyApplication;
import com.qian.base.BaseActivity;
import com.qian.utils.ApiException;
import com.qian.utils.WindowUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by master on 2017/8/14.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {
    private SubscriberOnNextListener mSubscriberOnNextListener;



    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }

    private void showProgressDialog(){
        if (null!=MyApplication.getCurrentActivity()){
            if (MyApplication.getCurrentActivity() instanceof BaseActivity){
                BaseActivity baseActivity = (BaseActivity) MyApplication.getCurrentActivity();
                baseActivity.showProgress();
            }
        }
    }

    private void dismissProgressDialog(){
        if (MyApplication.getCurrentActivity() instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity) MyApplication.getCurrentActivity();
            baseActivity.dismissProgress();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        LogUtils.e(e);
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showLong("网络中断，请检查您的网络状态");
          //  Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            ToastUtils.showLong("网络中断，请检查您的网络状态");
          //  Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            ToastUtils.showLong(e.getMessage());
          //  Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.error(e.getMessage());
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
}
