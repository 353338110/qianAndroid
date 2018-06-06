package com.qian.oksocket;

import com.qian.utils.ByteUtil;
import com.shrikanthravi.chatview.data.Message;
import com.xuhao.android.libsocket.sdk.bean.ISendable;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2018/6/5.
 */

public class SendBean implements ISendable {
    Message message;

    public SendBean(Message message) {
        this.message = message;
    }

    @Override
    public byte[] parse() {
        byte[] body = message.getBody().getBytes(Charset.defaultCharset());
        ByteBuffer bb = ByteBuffer.allocate(9+body.length);
        bb.put(ByteUtil.shortToByteArray(ProtocolHeader.MAGIC));
        bb.put(ProtocolHeader.REQUEST);
        bb.put(ProtocolHeader.PERSON_MESSAGE);
        bb.put(ProtocolHeader.SUCCESS);
        bb.put(ByteUtil.intToByteArray(body.length));
        bb.put(body);
        return bb.array();
    }
}
