package com.qian.FragmentUtil;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SHCai on 2017/8/25.
 */

public class CommLazyPagerAdapter extends LazyFragmentPagerAdapter{

    List<Fragment> fragmentList;
    Context mContext;

    public CommLazyPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }
/*    public CommLazyPagerAdapter(Context mContext,List<Fragment> fragmentList) {
        this.mContext = mContext;
        this.fragmentList = fragmentList;
    }*/


    @Override
    public Fragment getItem(ViewGroup container, int position) {
        //// TODO: 2017/8/25  返回fragment的view
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
