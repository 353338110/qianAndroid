package com.qian.contract;

import com.qian.base.IView;
import com.qian.bean.MoodLog;
import com.qian.bean.Pager;

import java.util.List;

/**
 * Created by master on 2017/10/18.
 */

public interface IMoodContract {
    interface View extends IView{

        int getCurrentPager();

        void getMoodLog(Pager<MoodLog> pager);
    }

    interface Presenter {

    }
}
