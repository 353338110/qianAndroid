package com.qian.contract;

import com.qian.base.BasePresenter;
import com.qian.base.IView;
import com.qian.bean.kaiyan.Replies;

import java.util.List;

/**
 * Created by master on 2017/9/9.
 */

public interface IReplyContract {
    interface View extends IView{
        int getId();
        int getLastId();
        void getReply(Replies replies);
        void getMoreReply(Replies replies);
    }

    interface Presenter {
        void reply();
        void replyMore();
    }
}
