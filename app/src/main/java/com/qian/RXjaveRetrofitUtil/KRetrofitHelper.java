package com.qian.RXjaveRetrofitUtil;

import com.qian.Bean.HttpResult.HttpResult;
import com.qian.Bean.Kaiyan.Daily;
import com.qian.Bean.User;
import com.qian.IService.KaiyanService;
import com.qian.IService.QianService;
import com.qian.Utils.ApiException;

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
 * Created by SHCai on 2017/8/22.
 */

public class KRetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    OkHttpClient.Builder builder;
    private KaiyanService kaiyanService;

    /**
     * 获取RetrofitHelper对象的单例
     * */
    private static class Singleton {
        private static final KRetrofitHelper INSTANCE = new KRetrofitHelper();
    }

    public static KRetrofitHelper getInstance() {
        return KRetrofitHelper.Singleton.INSTANCE;
    }

    public KRetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .baseUrl(KaiyanService.BASE_URL)
                .build();
        kaiyanService = retrofit.create(KaiyanService.class);
    }

    private class CallResult<T> implements Func1<T, T> {
        @Override
        public T call(T t) {
            return t;
        }
    }
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void getDaily(ProgressSubscriber<Daily> subscriber){
        Observable observable = kaiyanService.getDaily()
                .map(new CallResult<>());
        toSubscribe(observable, subscriber);
    }
}
