package com.qian.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.qian.Base.BaseFragment;
import com.qian.Bean.Kaiyan.Daily;
import com.qian.Bean.Kaiyan.IssuList;
import com.qian.Bean.Kaiyan.ItemList;
import com.qian.FragmentUtil.LazyFragmentPagerAdapter;
import com.qian.R;
import com.qian.RXjaveRetrofitUtil.KRetrofitHelper;
import com.qian.RXjaveRetrofitUtil.ProgressSubscriber;
import com.qian.RXjaveRetrofitUtil.SubscriberOnNextListener;

import java.util.Iterator;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eyepetizer;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        KRetrofitHelper.getInstance().getDaily(new ProgressSubscriber<Daily>(new SubscriberOnNextListener<Daily>() {
            @Override
            public void onNext(Daily daily) {
                /*for (IssuList i:daily.getIssueList()) {
                     for (ItemList k: i.getItemList()) {
                         Iterator<ItemList> stuIter = i.getItemList().iterator();
                         while (stuIter.hasNext()) {
                             ItemList student = stuIter.next();
                             if (!student.type.equals("video"))
                                 stuIter.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                         }
                    }
                }*/
                LogUtils.w(daily.toString());
            }
        }));
    }

}
