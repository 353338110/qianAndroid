package com.qian.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qian.Base.BaseFragment;
import com.qian.FragmentUtil.LazyFragmentPagerAdapter;
import com.qian.R;

import butterknife.BindView;

/**
 * Created by SHCai on 2017/8/25.
 */

public class EyepetizerFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable{
    @BindView(R.id.text)
    TextView text;

    String str;
    public EyepetizerFragment() {

    }
    @SuppressLint("ValidFragment")
    public EyepetizerFragment(String str) {
        this.str = str;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eyepetizer;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        text.setText("第 "+str+" 个fragmetn");
    }

    @Override
    protected void initData() {

    }


}
