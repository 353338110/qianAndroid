package com.qian.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.qian.base.BasePresenter;
import com.qian.bean.kaiyan.Daily;
import com.qian.bean.kaiyan.IssuList;
import com.qian.bean.kaiyan.ItemList;
import com.qian.contract.IEyepetizerContract;
import com.qian.fragment.EyepetizerFragment;
import com.qian.utils.MyStringUtil;
import com.qian.utils.rxJaveRetrofitUtil.KRetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;

import java.util.Iterator;

/**
 * Created by master on 2017/9/5.
 */

public class EyepetizerPresenter extends BasePresenter<EyepetizerFragment> implements IEyepetizerContract.Presenter {
    Daily lastDaily;
    String dateTime;
    @Override
    public void getDaily() {
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
                getIView().setDaily(daily);
                LogUtils.w(daily.toString());
            }

            @Override
            public void error(String error) {

            }
        }));

    }

    @Override
    public void getMoreDaily() {
        String nextPageUrl = lastDaily.getNextPageUrl();
        dateTime = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1,
                nextPageUrl.indexOf("&"));
        LogUtils.w("dateTime = "+dateTime);
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
                getIView().setMoreDaily(daily);
                LogUtils.w(daily.toString());
            }

            @Override
            public void error(String error) {

            }
        }),dateTime);
    }


}
