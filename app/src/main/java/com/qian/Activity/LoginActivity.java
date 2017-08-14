package com.qian.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.qian.Base.BaseActivity;
import com.qian.Bean.User;
import com.qian.R;
import com.qian.RXjaveRetrofitUtil.MySubscriber;
import com.qian.RXjaveRetrofitUtil.ProgressSubscriber;
import com.qian.RXjaveRetrofitUtil.RetrofitHelper;
import com.qian.RXjaveRetrofitUtil.SubscriberOnNextListener;
import com.qian.Utils.WindowUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by SHCai on 2017/8/13.
 */

public class LoginActivity extends BaseActivity {
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
        username = etName.getText().toString();
        password = etPassword.getText().toString();

        if (null!=username && !"".equals(username)){
            if (null!=password && !"".equals(password)){
                RetrofitHelper.getInstance().login(new ProgressSubscriber<User>(new SubscriberOnNextListener<User>() {
                            @Override
                            public void onNext(User user) {
                                LogUtils.w("onNext = " + user.toUserString());
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
        }
    }
}
