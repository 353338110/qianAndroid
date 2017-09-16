package com.qian.utils;

import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.qian.MyApplication;

/**
 * Created by master on 2017/8/14.
 */

public class ApiException extends RuntimeException{

    private static String msg;
    private static int status;

    public ApiException(int status,String msg) {
        this(getApiExceptionMessage(status,msg));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return msg;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param status
     * @return
     */
    private static String getApiExceptionMessage(int status,String message){
       /* switch (code) {
            case 1:
                msg = "该用户不存在";
                break;
            case 2:
                msg = "密码错误";
                break;
            default:
                msg = "未知错误";
        }*/
       msg = message;
       MyApplication.getCurrentActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               ToastUtils.showLong(msg);
           }
       });

        return msg;
    }

}
