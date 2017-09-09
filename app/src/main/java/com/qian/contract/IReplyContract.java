package com.qian.contract;

import com.qian.base.BasePresenter;
import com.qian.base.IView;

/**
 * Created by master on 2017/9/9.
 */

public interface IReplyContract {
    interface View extends IView{
        int getId();
        int getLastId();
    }

    interface Presenter {
        void reply();
        void replyMore();
    }
}
