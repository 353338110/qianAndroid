package com.qian.presenter;

import com.qian.activity.MovieDetailActivity;
import com.qian.base.BasePresenter;
import com.qian.bean.kaiyan.Daily;
import com.qian.bean.kaiyan.Replies;
import com.qian.contract.IEyepetizerContract;
import com.qian.contract.IReplyContract;
import com.qian.fragment.EyepetizerFragment;
import com.qian.utils.rxJaveRetrofitUtil.KRetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;

/**
 * Created by master on 2017/9/9.
 */

public class ReplyPresenter extends BasePresenter<MovieDetailActivity> implements IReplyContract.Presenter {
    @Override
    public void reply() {
        KRetrofitHelper.getInstance().fetchReplies(new ProgressSubscriber<Replies>(new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void error(String error) {

            }
        }),getIView().getId());
    }

    @Override
    public void replyMore() {
        KRetrofitHelper.getInstance().fetchReplies(new ProgressSubscriber<Replies>(new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void error(String error) {

            }
        }),getIView().getId(),getIView().getLastId());
    }
}
