package com.qian.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.qian.Base.BaseActivity;
import com.qian.Fragment.EyepetizerFragment;
import com.qian.FragmentUtil.CommLazyPagerAdapter;
import com.qian.FragmentUtil.LazyPagerAdapter;
import com.qian.FragmentUtil.LazyViewPager;
import com.qian.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.lz_vp)
    LazyViewPager lzVp;
    @BindView(R.id.bottom_nav)
    BottomNavigationBar bottomNav;

    List<Fragment> fragments = new ArrayList<>();
    CommLazyPagerAdapter commLazyPagerAdapter;

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
        InitNavigationBar(bottomNav);
        for (int i = 0; i < 3; i++) {
            fragments.add(new EyepetizerFragment(i+1+""));
        }
        commLazyPagerAdapter = new CommLazyPagerAdapter(mContext,fragments);
        lzVp.setAdapter(commLazyPagerAdapter);
        lzVp.setCurrentItem(0);
    }
    private void InitNavigationBar(BottomNavigationBar mBottomNavigationBar) {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.bottom1, "Eyepetizer").setActiveColorResource(R.color.bottom1))
                .addItem(new BottomNavigationItem(R.mipmap.bottom2, "Whispers").setActiveColorResource(R.color.bottom2))
                .addItem(new BottomNavigationItem(R.mipmap.bottom3, "Settings").setActiveColorResource(R.color.bottom3))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {
        lzVp.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /*@OnClick({R.id.text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
                KRetrofitHelper.getInstance().getDaily(new ProgressSubscriber<Daily>(new SubscriberOnNextListener<Daily>() {
                    @Override
                    public void onNext(Daily daily) {
                        LogUtils.w(daily.toString());
                    }
                }));
                break;
        }
    }*/
}
