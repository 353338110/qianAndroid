package com.qian.utils.rxJaveRetrofitUtil;

/**
 * Created by master on 2017/8/14.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void error(String error);
}
