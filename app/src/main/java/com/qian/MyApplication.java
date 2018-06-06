package com.qian;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.danikula.videocache.HttpProxyCacheServer;
import com.xuhao.android.libsocket.sdk.OkSocket;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by master on 2017/8/14.
 */

public class MyApplication extends MultiDexApplication {

    //对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList实现了基于动态数组的数据结构，要移动数据。LinkedList基于链表的数据结构,便于增加删除
    public static List<Activity> activityList = new LinkedList<Activity>();
    private static Context context;
    public MyApplication() {
        super();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //在主进程初始化一次,多进程时需要区分主进程.
        //OkSocket.initialize(this);
        //如果需要开启Socket调试日志,请配置
        OkSocket.initialize(this,true);
    }


    /**
     * 获得当前app运行的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }
    //添加Activity到容器中
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }
    public static void remove(Activity activity){
        activityList.remove(activity);
    }
    //遍历所有Activity并finish
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
    public static void finish(){
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
    public static Activity getCurrentActivity(){
        if (null!=activityList && activityList.size()>0){
            return activityList.get(activityList.size()-1);
        }
        return null;
    }

    private HttpProxyCacheServer cacheServer;


    public static HttpProxyCacheServer cacheServer(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return app.cacheServer == null ? app.cacheServer = app.newCacheServer() : app.cacheServer;
    }

    private HttpProxyCacheServer newCacheServer() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
                .build();
    }

}
