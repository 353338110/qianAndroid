package com.qian.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.qian.Base.BaseActivity;
import com.qian.CustomView.LVGhost;
import com.qian.R;
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
                break;
    }
    }
}
