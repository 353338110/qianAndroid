package com.qian.iService;

import com.qian.bean.httpResult.HttpResult;
import com.qian.bean.User;

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
    //String BASE_URL = "http://192.168.1.122:8080/";
    String BASE_URL = "http://116.196.82.151:8080/mmall/";

    @FormUrlEncoded
    @POST("user/login.do")
    Observable<HttpResult<User>> login(@Field("username") String username, @Field("password") String password);

}
