package com.tanyuan.network.request;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyuan.network.interfaces.EndpointRequest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/11/16.
 */

public class RequestManager {

   public RequestManager(){

    }

    public static void loadData(String url, EndpointRequest requestModel){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        ObjectMapper mMapper= new ObjectMapper();
        mMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String bodyStr = null;
        try {
            bodyStr = mMapper.writeValueAsString(requestModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),bodyStr);
        RequestBody body = new FormBody.Builder().build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    Boolean isSuccess = response.isSuccessful();
                    Log.d("requestTag",isSuccess+"请求结果");
            }
        };
        call.enqueue(callback);
//        Response response = null;
//        try {
//            response = call.execute();
//            Log.d("requestTag",response.body().string()+"请求结果");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static void requestByGet(NetRequest r){
        String url = "http://api.qiuapp.cn/app/shop/detail?lon=121.92647560635912&lat=30.899331219193396&shopId=57&token=dd68306e7dab489b81860f26377a1225";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(r.getPath())
//                .addHeader("Accept","*/*")
//                .addHeader("Content-Type","application/x-www-form-urlencoded")
//                .addHeader("token","5773b595d47642979e6398e46fa05523")
//                .addHeader("userId","25876475")
                .headers(r.getHeaders())
                .build();
        Call call = okHttpClient.newCall(request);
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Boolean isSuccess = response.isSuccessful();
                Log.d("requestTag",response.body().string()+"请求结果");
            }
        };
        call.enqueue(callback);
//        try {
//            Response response = call.execute();
//            Log.e("requestTag",response.body().string()+"请求结果");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
