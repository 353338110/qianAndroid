package com.qian.Bean.Kaiyan;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHCai on 2017/8/22.
 */

public class IssuList {

    /**
     * releaseTime : 1503363600000
     * type : morning
     * date : 1503363600000
     * publishTime : 1503363600000
     * itemList : []
     * count : 10
     */

    private long releaseTime;
    private long date;
    private long publishTime;
    private int count;
    private List<ItemList> itemList;

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }



    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }
}
