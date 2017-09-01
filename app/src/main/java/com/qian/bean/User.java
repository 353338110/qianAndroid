package com.qian.bean;

/**
 * Created by SHCai on 2017/8/13.
 */

public class User {

    /**
     * id : 1
     * username : 大涵哥
     * password :
     * phone : null
     * createTime : 1502201570000
     * updateTime : 1502201582000
     * role : 0
     */

    private int id;
    private String username;
    private String password;
    private String phone;
    private long createTime;
    private long updateTime;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public String toUserString(){
        return "username = " +username
                +"\npassword = " +password
                +"\nphone = " +phone
                +"\ncreateTime = " +createTime
                +"\nupdateTime = " +updateTime
                +"\nrole = " +role;
    }
}

