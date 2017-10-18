package com.qian.iService;

import com.qian.bean.Mixed;
import com.qian.bean.MoodLog;
import com.qian.bean.MoodLogPager;
import com.qian.bean.Pager;
import com.qian.bean.UIDUser;
import com.qian.bean.httpResult.HttpResult;
import com.qian.bean.User;

import java.io.File;
import java.util.List;
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
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SHCai on 2017/8/13.
 */

public interface QianService {

    //192.168.1.100
    //10.0.2.2
    //192.168.1.122   公司ip
    //114.67.129.197 云主机
    //String BASE_URL = "http://192.168.1.122:8080/";//公司ip 不用mmall
    String BASE_URL = "http://114.67.129.197:8080/mmall/"; //云服务器 需要mmall
    //String BASE_URL = "http://116.196.82.151:8080/mmall/";

    @FormUrlEncoded
    @POST("user/login.do")
    Observable<HttpResult<User>> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("user/login2.do")
    Observable<HttpResult<UIDUser>> loginByPhone(@Field("phone") String phone, @Field("password") String password);


    @Multipart
    @POST("moodlog/mood.do")
    Observable<HttpResult<String>> uploadMood(@Part("uid")String uid,@Part("title") String title, @Part("content") String content, @PartMap Map<String, RequestBody> files);

    @FormUrlEncoded
    @POST("moodlog/query_mood.do")
    Observable<HttpResult<Pager<MoodLog>>> queryMood(@Field("uid") String uid, @Query("currentPage") int currentPage, @Query("pageSize") int pageSize);
}
