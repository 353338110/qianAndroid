package com.qian.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.qian.base.BasePresenter;
import com.qian.bean.MoodLog;
import com.qian.bean.Pager;
import com.qian.bean.UIDUser;
import com.qian.contract.IMoodContract;
import com.qian.fragment.MoodLogFragment;
import com.qian.utils.Constants;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.RetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;

import java.util.List;

/**
 * Created by master on 2017/10/18.
 */

public class MoodLogPresenter extends BasePresenter<MoodLogFragment> implements IMoodContract.Presenter {
    public void queryMood(){
        RetrofitHelper.getInstance().queryMood(new ProgressSubscriber<Pager<MoodLog>>(new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                LogUtils.w("---"+o);
                Pager<MoodLog> pager = (Pager<MoodLog>) o;
                getIView().getMoodLog(pager);
            }

            @Override
            public void error(String error) {
                LogUtils.w("---"+error);
            }
        }), Constants.uidUser.getUid(),getIView().getCurrentPager(),Constants.PAGERSIZE);
    }
}
