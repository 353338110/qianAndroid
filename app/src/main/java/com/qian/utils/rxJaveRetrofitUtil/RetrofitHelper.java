package com.qian.utils.rxJaveRetrofitUtil;

import com.qian.bean.httpResult.HttpResult;
import com.qian.bean.User;
import com.qian.iService.QianService;
import com.qian.utils.ApiException;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by SHCai on 2017/8/13.
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    OkHttpClient.Builder builder;
    private QianService qianService;

    /**
     * 获取RetrofitHelper对象的单例
     * */
    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return Singleton.INSTANCE;
    }

    public RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .baseUrl(QianService.BASE_URL)
                .build();
        qianService = retrofit.create(QianService.class);
    }
   /* *//**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     *//*
    public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {
        return new Observable.Transformer<HttpResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResult<T>> tObservable) {
                return tObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResult<T> result) {
                        LogUtils.e(result.getStatus()+"");
                        if (result.getStatus() == 0) {
                            return createData(result.getData());
                        } else {
                            return Observable.error(new ApiException(result.getStatus(),result.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    *//**
     * 创建成功的数据
     *
     * @return
     *//*
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }*/

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getStatus() != 0) {
                throw new ApiException(httpResult.getStatus(),httpResult.getMsg());
            }
            return httpResult.getData();
        }
    }
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void login(Subscriber<User> subscriber, String username, String password){
        Observable observable = qianService.login(username, password)
                .map(new HttpResultFunc<User>());
        toSubscribe(observable, subscriber);
    }
    public void login(ProgressSubscriber<User> subscriber, String username, String password){
        Observable observable = qianService.login(username, password)
                .map(new HttpResultFunc<User>());
        toSubscribe(observable, subscriber);
    }
    public void loginByPhone(Subscriber<User> subscriber, String phone, String password){
        Observable observable = qianService.loginByPhone(phone, password)
                .map(new HttpResultFunc<User>());
        toSubscribe(observable, subscriber);
    }
    public void loginByPhone(ProgressSubscriber<User> subscriber, String phone, String password){
        Observable observable = qianService.loginByPhone(phone, password)
                .map(new HttpResultFunc<User>());
        toSubscribe(observable, subscriber);
    }
}
