package com.qian.utils.rxJaveRetrofitUtil;

import com.luck.picture.lib.entity.LocalMedia;
import com.qian.bean.ChoosePicBean;
import com.qian.bean.MoodLog;
import com.qian.bean.Pager;
import com.qian.bean.UIDUser;
import com.qian.bean.httpResult.HttpResult;
import com.qian.bean.User;
import com.qian.iService.QianService;
import com.qian.utils.ApiException;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
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

    public static MultipartBody filesToMultipartBody(List<ChoosePicBean> choosePicBeanList) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (ChoosePicBean choosePicBean : choosePicBeanList) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            if (null!=choosePicBean.getCompressPath()){
                File file = new File(choosePicBean.getCompressPath());
                if (file!=null){
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                    builder.addFormDataPart("file",file.getName() , requestBody);
                }
            }

        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
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
    public void loginByPhone(Subscriber<UIDUser> subscriber, String phone, String password){
        Observable observable = qianService.loginByPhone(phone, password)
                .map(new HttpResultFunc<UIDUser>());
        toSubscribe(observable, subscriber);
    }
    public void loginByPhone(ProgressSubscriber<UIDUser> subscriber, String phone, String password){
        Observable observable = qianService.loginByPhone(phone, password)
                .map(new HttpResultFunc<UIDUser>());
        toSubscribe(observable, subscriber);
    }

    public void uploadMood(ProgressSubscriber<String> subscriber, String uid, String title, String content,Map<String, RequestBody> requestBodyMap){
        Observable observable = qianService.uploadMood(uid,title,content, requestBodyMap)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable, subscriber);
    }

    public void queryMood(ProgressSubscriber<Pager<MoodLog>> subscriber, String uid,int currentPage,int pageSize ){
        Observable observable = qianService.queryMood(uid,currentPage,pageSize)
                .map(new HttpResultFunc<Pager<MoodLog>>());
        toSubscribe(observable, subscriber);
    }
}
