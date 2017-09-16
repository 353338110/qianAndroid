package com.qian.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.qian.activity.LoginActivity;
import com.qian.base.BasePresenter;
import com.qian.bean.User;
import com.qian.contract.ILoginContract;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.RetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;

/**
 * Created by master on 2017/9/5.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements ILoginContract.Presenter {
    @Override
    public void login() {
        if (null==getIView().getPhone() || "".equals(getIView().getPhone())){
            ToastUtils.showLong("请输入账号");
            return;
        }
        if (null==getIView().getPassword() || "".equals(getIView().getPassword())){
            ToastUtils.showLong("请输入密码");
            return;
        }
        RetrofitHelper.getInstance().loginByPhone(new ProgressSubscriber<User>(new SubscriberOnNextListener<User>() {
            @Override
            public void onNext(User user) {

                LogUtils.w("onNext = " + user.toUserString());
                getIView().loginSuccess(user);
            }

            @Override
            public void error(String error) {
                getIView().loginError(error);
            }
        }), getIView().getPhone(), getIView().getPassword());
    }
}
