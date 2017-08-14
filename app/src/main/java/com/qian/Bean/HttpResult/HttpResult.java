package com.qian.Bean.HttpResult;

/**
 * Created by SHCai on 2017/8/13.
 */

/**
 * 封装的返回数据
 * 其中data是返回的数据
 * 其他是返回数据公共的部分
 */
public class HttpResult<T> {

    /**
     * status : 0
     * msg : 登陆成功
     * T data
     */

    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
