package com.qian.bean;

/**
 * Created by master on 2017/9/16.
 */

public class Mixed {
    private String id;
    private String userId;
    private long createTime;
    private long updateTime;
    private String mixedtext;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getMixedtext() {
        return mixedtext;
    }

    public void setMixedtext(String mixedtext) {
        this.mixedtext = mixedtext;
    }

    public String toMixedString() {
        return "id = " + id
                + "\nuserId = " + userId
                + "\ncreateTime = " + createTime
                + "\nupdateTime = " + updateTime
                + "\nmixedtext = " + mixedtext;
    }
}
