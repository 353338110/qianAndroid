package com.qian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.qian.base.BaseActivity;
import com.qian.bean.User;
import com.qian.R;
import com.qian.contract.ILoginContract;
import com.qian.presenter.LoginPresenter;
import com.qian.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by SHCai on 2017/8/13.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.View{
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    String username ;
    String password ;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView(View view) {
        if (!"".equals(SPUtils.getInstance().getString("username"))){
            etName.setText(SPUtils.getInstance().getString("username"));
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mPresenter.login();
        /*username = etName.getText().toString();
        password = etPassword.getText().toString();

        if (null!=username && !"".equals(username)){
            if (null!=password && !"".equals(password)){
                RetrofitHelper.getInstance().login(new ProgressSubscriber<User>(new SubscriberOnNextListener<User>() {
                            @Override
                            public void onNext(User user) {
                                LogUtils.w("onNext = " + user.toUserString());
                                Constants.user = user;
                                SPUtils.getInstance().put("username",username);
                                SPUtils.getInstance().put("password",password);

                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }), username, password);
            }else {
                ToastUtils.showLong("别偷懒，快输入密码");
            }
        }else {
            ToastUtils.showLong("别偷懒，快输入账号");
        }*/
    }

    @Override
    public String getUsername() {
        return etName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void loginSuccess(User user) {
        Constants.user = user;
        SPUtils.getInstance().put("username",getUsername());
        SPUtils.getInstance().put("password",getPassword());

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError(String error) {

    }

}
