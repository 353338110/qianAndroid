package com.qian.bean;

/**
 * Created by master on 2017/9/29.
 */

public class UIDUser {
    private String uid;
    private User user;

    public UIDUser(String uid, User user) {
        this.uid = uid;
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toUserString(){
        return "uid = " +uid
                +"\nusername = " +user.getUsername()
                +"\npassword = " +user.getPassword()
                +"\nphone = " +user.getPhone()
                +"\ncreateTime = " +user.getCreateTime()
                +"\nupdateTime = " +user.getUpdateTime()
                +"\nrole = " +user.getRole();
    }
}
