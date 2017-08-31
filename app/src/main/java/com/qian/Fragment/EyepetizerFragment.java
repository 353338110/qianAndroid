package com.qian.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qian.Adapter.DailyAdapter;
import com.qian.Base.BaseFragment;
import com.qian.Bean.Kaiyan.Daily;
import com.qian.Bean.Kaiyan.IssuList;
import com.qian.Bean.Kaiyan.ItemList;
import com.qian.FragmentUtil.LazyFragmentPagerAdapter;
import com.qian.R;
import com.qian.RXjaveRetrofitUtil.KRetrofitHelper;
import com.qian.RXjaveRetrofitUtil.ProgressSubscriber;
import com.qian.RXjaveRetrofitUtil.SubscriberOnNextListener;
import com.qian.Utils.MyStringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SHCai on 2017/8/25.
 */

public class EyepetizerFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable {

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

    private String dateTime = "";
    Daily lastDaily;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        dailyAdapter = new DailyAdapter();
        dailyAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rcvEye.setAdapter(dailyAdapter);
        rcvEye.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        KRetrofitHelper.getInstance().getDaily(new ProgressSubscriber<Daily>(new SubscriberOnNextListener<Daily>() {
            @Override
            public void onNext(Daily daily) {
                lastDaily = daily;
                for (IssuList i : daily.getIssueList()) {
                    Iterator<ItemList> stuIter = i.getItemList().iterator();
                    while (stuIter.hasNext()) {
                        ItemList itemList = stuIter.next();
                        if (!itemList.type.equals("video"))
                            stuIter.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                    }
                }
                dailys.addAll(MyStringUtil.getItemList(daily));
                dailyAdapter.addData(dailys);
                dailyAdapter.notifyDataSetChanged();
                LogUtils.w(daily.toString());
            }
        }));
    }

    @Override
    protected void initEvent() {
        dailyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.w("滑动底部");
                String nextPageUrl = lastDaily.getNextPageUrl();
                dateTime = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1,
                        nextPageUrl.indexOf("&"));
                LogUtils.w("dateTime = "+dateTime);
                KRetrofitHelper.getInstance().getDaily(new ProgressSubscriber<Daily>(new SubscriberOnNextListener<Daily>() {
                    @Override
                    public void onNext(Daily daily) {
                       dailyAdapter.loadMoreComplete();
                        lastDaily = daily;
                        for (IssuList i : daily.getIssueList()) {
                            Iterator<ItemList> stuIter = i.getItemList().iterator();
                            while (stuIter.hasNext()) {
                                ItemList itemList = stuIter.next();
                                if (!itemList.type.equals("video"))
                                    stuIter.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                            }
                        }
                        dailys.addAll(MyStringUtil.getItemList(daily));
                        dailyAdapter.addData(dailys);
                        dailyAdapter.notifyDataSetChanged();
                        LogUtils.w(daily.toString());
                    }
                }),dateTime);
            }
        },rcvEye);
    }

}
