package com.qian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.ToastUtils;
import com.lauzy.freedom.lbehaviorlib.behavior.CommonBehavior;
import com.nostra13.universalimageloader.utils.L;
import com.qian.base.BaseActivity;
import com.qian.base.BasePresenter;
import com.qian.fragment.EyepetizerFragment;
import com.qian.fragment.MoodLogFragment;
import com.qian.fragment.WhispersFragment;

import com.qian.oksocket.OkSocketAdapter;
import com.qian.oksocket.SendBean;
import com.qian.utils.Constants;
import com.qian.utils.fragmentUtil.CommLazyPagerAdapter;
import com.qian.utils.fragmentUtil.LazyViewPager;
import com.qian.R;
import com.xuhao.android.libsocket.sdk.ConnectionInfo;
import com.xuhao.android.libsocket.sdk.OkSocketOptions;
import com.xuhao.android.libsocket.sdk.connection.IConnectionManager;
import com.xuhao.android.libsocket.sdk.protocol.IHeaderProtocol;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.xuhao.android.libsocket.sdk.OkSocket.open;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.lz_vp)
    LazyViewPager lzVp;
    @BindView(R.id.bottom_nav)
    BottomNavigationBar bottomNav;
    List<Fragment> fragments = new ArrayList<>();
    CommLazyPagerAdapter commLazyPagerAdapter;

    View toolBarLayout;


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
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {
        toolBarLayout = findViewById(R.id.layout_toolbar);
        toolBarLayout.setVisibility(View.GONE);
    }

    public void setToolBarLayoutVisible(boolean isVisible){
        if (isVisible){
            toolBarLayout.setVisibility(View.VISIBLE);
        }else {
            toolBarLayout.setVisibility(View.GONE);
        }
    }
 /*   @Override
    public void setSteepStatusBar(boolean isSetStatusBar) {
        super.setSteepStatusBar(true);
    }

    @Override
    public void setAllowFullScreen(boolean allowFullScreen) {
        super.setAllowFullScreen(true);
    }*/



    @Override
    public void doBusiness(Context mContext) {
        InitNavigationBar(bottomNav);
        fragments.add(new EyepetizerFragment());
        fragments.add(new MoodLogFragment());
        fragments.add(new WhispersFragment());
        commLazyPagerAdapter = new CommLazyPagerAdapter(getSupportFragmentManager(),fragments);
        lzVp.setAdapter(commLazyPagerAdapter);
        lzVp.setCurrentItem(0);
        CommonBehavior.from(bottomNav)
                .setMinScrollY(20)
                .setScrollYDistance(100)
                .setDuration(1000)
                .setInterpolator(new LinearOutSlowInInterpolator());

    }
    private void InitNavigationBar(BottomNavigationBar mBottomNavigationBar) {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_github_circle_white_24dp, "Eyepetizer").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "MoodLog").setActiveColorResource(R.color.bottom2))
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "Whispers").setActiveColorResource(R.color.bottom3))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {
        ToastUtils.showLong("当前是第"+position+"个fragment");
        lzVp.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
    }


}
