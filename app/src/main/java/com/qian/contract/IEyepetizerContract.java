package com.qian.contract;

import com.qian.bean.kaiyan.Daily;

import retrofit2.http.GET;

/**
 * Created by master on 2017/9/5.
 */

public interface IEyepetizerContract {

    interface View {
        void setDaily(Daily daily);
        void setMoreDaily(Daily daily);
    }

    interface Presenter {
        void getDaily();
        void getMoreDaily();
    }
}
