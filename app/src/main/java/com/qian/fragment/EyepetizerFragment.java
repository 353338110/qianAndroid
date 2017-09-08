package com.qian.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qian.adapter.DailyAdapter;
import com.qian.base.BaseFragment;
import com.qian.base.BasePresenter;
import com.qian.base.IView;
import com.qian.bean.kaiyan.Daily;
import com.qian.bean.kaiyan.IssuList;
import com.qian.bean.kaiyan.ItemList;
import com.qian.contract.IEyepetizerContract;
import com.qian.presenter.EyepetizerPresenter;
import com.qian.utils.fragmentUtil.LazyFragmentPagerAdapter;
import com.qian.R;
import com.qian.utils.rxJaveRetrofitUtil.KRetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;
import com.qian.utils.MyStringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by SHCai on 2017/8/25.
 */

public class EyepetizerFragment extends BaseFragment<EyepetizerPresenter> implements LazyFragmentPagerAdapter.Laziable,IEyepetizerContract.View {

    @BindView(R.id.rcv_eye)
    RecyclerView rcvEye;
    @BindView(R.id.sfl_eye)
    SwipeRefreshLayout sflEye;

    List<ItemList> dailys = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eyepetizer;
    }

    DailyAdapter dailyAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected EyepetizerPresenter loadPresenter() {
        return new EyepetizerPresenter();
    }

    @Override
    protected void initData() {
        dailyAdapter = new DailyAdapter(R.layout.item_daily,dailys);
        /*dailyAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        dailyAdapter.setNotDoAnimationCount(3);*/
        rcvEye.setAdapter(dailyAdapter);
        rcvEye.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        mPresenter.getDaily();
    }

    @Override
    protected void initEvent() {
        dailyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.w("滑动底部");
                mPresenter.getMoreDaily();
            }
        },rcvEye);
    }

    @Override
    public void setDaily(Daily daily) {
        dailys.clear();
        dailys.addAll(MyStringUtil.getItemList(daily));
        dailyAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMoreDaily(Daily daily) {
        dailyAdapter.loadMoreComplete();
        //dailys.addAll(MyStringUtil.getItemList(daily));
        dailyAdapter.addData(MyStringUtil.getItemList(daily));
        //dailyAdapter.notifyDataSetChanged();
    }
}
