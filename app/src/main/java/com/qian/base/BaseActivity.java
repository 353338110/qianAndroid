package com.qian.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.Utils;
import com.qian.utils.fragmentUtil.BackHandlerHelper;
import com.qian.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by SHCai on 2017/8/13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    /** 是否沉浸状态栏 **/
    private boolean isSetStatusBar = true;

    /** 是否允许全屏 **/
    private boolean mAllowFullScreen = true;
    /** 是否禁止旋转屏幕 **/
    private boolean isAllowScreenRoate = false;
    /** 当前Activity渲染的视图View **/
    private View mContextView = null;
    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    public BaseActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Log.d(TAG, "BaseActivity-->onCreate()");
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this)
                    .inflate(bindLayout(), null);
        } else{
            mContextView = mView;
        }
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView(mContextView);
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        Utils.init(mContext);
        ButterKnife.bind(this);
        initView(mContextView);
        doBusiness(this);
        MyApplication.addActivity(this);
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [初始化参数]
     * 解析bundle内容或者设置是否旋转，沉浸，全屏
     * @param parms
     */
    public abstract void initParms(Bundle parms);

    /**
     * [绑定视图]
     *
     * @return
     */
    public abstract View bindView();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);


    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);






    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        MyApplication.remove(this);
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }


    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

}
