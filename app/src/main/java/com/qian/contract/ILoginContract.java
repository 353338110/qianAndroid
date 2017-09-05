package com.qian.contract;

import com.qian.activity.LoginActivity;
import com.qian.base.BasePresenter;
import com.qian.base.IView;
import com.qian.bean.User;

/**
 * Created by master on 2017/9/5.
 */

public interface ILoginContract {

    interface View extends IView{
        String getUsername();
        String getPassword();
        void loginSuccess(User user);
        void loginError(String error);
    }

    interface Presenter{
        void login();
    }
}
