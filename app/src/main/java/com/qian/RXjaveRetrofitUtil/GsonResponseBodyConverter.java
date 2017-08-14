package com.qian.RXjaveRetrofitUtil;

import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.qian.Bean.HttpResult.HttpResult;
import com.qian.MyApplication;
import com.qian.Utils.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by master on 2017/8/14.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        LogUtils.d("Network", "response>>" + response);
        //httpResult 只解析result字段
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        Toast.makeText(MyApplication.getCurrentActivity(),httpResult.getMsg(),Toast.LENGTH_LONG).show();
        if (httpResult.getStatus() != 0) {
            throw new ApiException(httpResult.getStatus(),httpResult.getMsg());
        }
        return gson.fromJson(response, type);


    }
}
