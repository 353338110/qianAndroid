package com.qian.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 2017/9/21.
 */

public class MoodLog {

    private String id;
    private int type;
    private String title;
    private String content;
    private String url;
    private long createTime;
    private long updateTime;
    /**
     * userid : 0000000000000001
     */

    private String userid;

    public MoodLog() {
        super();
    }

    public MoodLog(String id, String userid, int type, String title, String content, long createTime, long updateTime,String url) {
        this.id = id;
        this.userid = userid;
        this.type = type;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.url = url;
    }

    public static MoodLog objectFromData(String str) {

        return new Gson().fromJson(str, MoodLog.class);
    }

    public static MoodLog objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MoodLog.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MoodLog> arrayMoodLogFromData(String str) {

        Type listType = new TypeToken<ArrayList<MoodLog>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<MoodLog> arrayMoodLogFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MoodLog>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}
