package com.qian.oksocket;

import android.content.Context;

import com.nostra13.universalimageloader.utils.L;
import com.xuhao.android.libsocket.sdk.ConnectionInfo;
import com.xuhao.android.libsocket.sdk.SocketActionAdapter;
import com.xuhao.android.libsocket.sdk.bean.IPulseSendable;
import com.xuhao.android.libsocket.sdk.bean.ISendable;
import com.xuhao.android.libsocket.sdk.bean.OriginalData;

/**
 * Created by Administrator on 2018/6/5.
 */

public class OkSocketAdapter extends SocketActionAdapter {
    @Override
    public void onSocketDisconnection(Context context, ConnectionInfo info, String action, Exception e) {
        if (e != null) {
            L.w("okSocket 异常断开:" + e.getMessage());
        } else {
            L.w("okSocket 正常断开");
        }
    }

    @Override
    public void onSocketConnectionSuccess(Context context, ConnectionInfo info, String action) {
        L.w("okSocket","连接成功");
    }

    @Override
    public void onSocketConnectionFailed(Context context, ConnectionInfo info, String action, Exception e) {
        L.w("okSocket","连接失败"+e.toString());
    }

    @Override
    public void onSocketReadResponse(Context context, ConnectionInfo info, String action, OriginalData data) {
        super.onSocketReadResponse(context, info, action, data);
    }

    @Override
    public void onSocketWriteResponse(Context context, ConnectionInfo info, String action, ISendable data) {
        super.onSocketWriteResponse(context, info, action, data);
    }

    @Override
    public void onPulseSend(Context context, ConnectionInfo info, IPulseSendable data) {
        super.onPulseSend(context, info, data);
    }
}
