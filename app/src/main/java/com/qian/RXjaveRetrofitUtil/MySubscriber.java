package com.qian.RXjaveRetrofitUtil;

import rx.Subscriber;

/**
 * Created by master on 2017/8/14.
 */

public class MySubscriber<T> extends Subscriber<T> {
    public ISubscriber<T> iSubscriber;
    public interface ISubscriber<T> {
        void onNext(T t);
    };
    public MySubscriber(ISubscriber<T> iSubscriber) {
        this.iSubscriber = iSubscriber;
    }
    public ISubscriber2<T> iSubscriber2;
    public interface ISubscriber2<T> {
        void onNext(T t);
        void onCompleted();
    };
    public MySubscriber(ISubscriber2<T> iSubscriber2) {
        this.iSubscriber2 = iSubscriber2;
    }


    @Override
    public void onCompleted() {
        if (null!=iSubscriber2){
            iSubscriber2.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        if (null!=iSubscriber){
            iSubscriber.onNext(t);
        }
        if (null!=iSubscriber2){
            iSubscriber2.onNext(t);
        }
    }

}
