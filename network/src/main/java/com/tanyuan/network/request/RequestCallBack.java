package com.tanyuan.network.request;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyuan.network.interfaces.RequestInterface;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/12/13.
 */

public class RequestCallBack<T> implements Callback {
    RequestInterface requestInterface;
    Class<T> clazz;

    public RequestCallBack(RequestInterface requestInterface,Class<T> clazz){
        this.requestInterface = requestInterface;
        this.clazz = clazz;
    }
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Boolean isSuccess = response.isSuccessful();
        if (isSuccess) {
            String json = response.body().string();
            Log.e("requestTag", json + "请求结果");
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                T object = objectMapper.readValue(json, clazz);
//                        Log.e("dataMessage ====== ",data.get("message").toString());
                requestInterface.onReceivedData(object);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            requestInterface.onErrorData(response);
        }
    }
}
