package com.qian.iService;

import com.qian.bean.kaiyan.Daily;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SHCai on 2017/8/22.
 */

public interface KaiyanService {
    String BASE_URL = "http://baobab.kaiyanapp.com/api/";
    @GET("v2/feed?num=2")
    Observable<Daily> getDaily(@Query("date") String date);

    @GET("v2/feed?num=2")
    Observable<Daily> getDaily();
}
