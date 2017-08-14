package com.qian.IService;

import com.qian.Bean.HttpResult.HttpResult;
import com.qian.Bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by SHCai on 2017/8/13.
 */

public interface QianService {

    //192.168.1.100
    //10.0.2.2
    //192.168.1.122
    String BASE_URL = "http://192.168.1.122:8080/";

    @FormUrlEncoded
    @POST("user/login.do")
    Observable<HttpResult<User>> login(@Field("username") String username, @Field("password") String password);

}
