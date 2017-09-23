package com.qian.iService;

import com.qian.bean.Mixed;
import com.qian.bean.MoodLog;
import com.qian.bean.httpResult.HttpResult;
import com.qian.bean.User;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by SHCai on 2017/8/13.
 */

public interface QianService {

    //192.168.1.100
    //10.0.2.2
    //192.168.1.122
    String BASE_URL = "http://192.168.1.122:8080/";
    //String BASE_URL = "http://116.196.82.151:8080/mmall/";

    @FormUrlEncoded
    @POST("user/login.do")
    Observable<HttpResult<User>> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("user/login2.do")
    Observable<HttpResult<User>> loginByPhone(@Field("phone") String phone, @Field("password") String password);


    @Multipart
    @POST("moodlog/mood.do")
    Observable<HttpResult<String>> uploadMood(@Part("title") String title, @Part("content") String content, @Part File[] files);
}
