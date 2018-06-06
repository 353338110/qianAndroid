package com.qian.oksocket;

/**
 * Created by Administrator on 2018/6/5.
 */

public class MessageHolder {
    // 消息标志
    private byte sign;
    // 消息类型
    private byte type;
    // 响应状态
    private byte status;
    // Json消息体
    private String body;


    public byte getSign() {
        return sign;
    }

    public void setSign(byte sign) {
        this.sign = sign;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    @Override
    public String toString() {
        return "MessageHolder{" +
                "sign=" + sign +
                ", type=" + type +
                ", status=" + status +
                ", body='" + body + '\'' +
                '}';
    }
}
