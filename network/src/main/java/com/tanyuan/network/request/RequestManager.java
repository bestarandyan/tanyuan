package com.tanyuan.network.request;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyuan.network.BuildConfig;
import com.tanyuan.network.Utils.JsonUtils;
import com.tanyuan.network.interfaces.EndpointRequest;
import com.tanyuan.network.interfaces.RequestConfig;
import com.tanyuan.network.interfaces.RequestInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
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

public class RequestManager<T> {

    public RequestInterface requestInterface;
    public Class<T> clazz;

    public static RequestManager builder(){
        return new RequestManager();
    }

    public RequestManager requestByGet(EndpointRequest netRequest) {
        String url = " http://api.qiuapp.cn/app/shop/detail?lon=121.92654942270235&lat=30.899247058039965&shopId=57";
        StringBuffer httpUrl = new StringBuffer();
        String host = BuildConfig.API_HOST;
        httpUrl.append(host);

//        String path = netRequest.getPath();
        RequestConfig config = netRequest.getClass().getAnnotation(RequestConfig.class);
        String path = config.path();
        if (!TextUtils.isEmpty(path)) {
            httpUrl.append("/");
            httpUrl.append(path);
        }


        String content = encodeParametersToString(getParams(netRequest));
        if (!TextUtils.isEmpty(content)) {
            httpUrl.append("?");
            httpUrl.append(content);
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .headers(netRequest.getHeaders())
                .build();
        Call call = okHttpClient.newCall(request);
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Boolean isSuccess = response.isSuccessful();
                if (isSuccess){
                    String json = response.body().string();
                    Log.e("requestTag", json + "请求结果");
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        T object = objectMapper.readValue(json,clazz);
//                        JSONObject data = new JSONObject(json.trim());// 这种方式也可行
                        requestInterface.onReceivedData(object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        call.enqueue(callback);
        return this;
    }

    public RequestManager setResponseInterface(Class<T> clazz,RequestInterface<T> responseInterface){
        this.clazz = clazz;
        this.requestInterface = responseInterface;
        return this;
    }

    public static void loadData(String url, EndpointRequest requestModel) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        ObjectMapper mMapper = new ObjectMapper();
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
                Log.d("requestTag", isSuccess + "请求结果");
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

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    private byte[] encodeParameters(Map<String, String> params) {
        String paramsEncoding = "UTF-8";
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            String msg = encodedParams.toString();
            if (msg.endsWith("&")) {
                msg = msg.substring(0, msg.length() - 1);
            }
            return msg.getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }


    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    private String encodeParametersToString(Map<String, String> params) {
        String paramsEncoding = "UTF-8";
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            String msg = encodedParams.toString();
            if (msg.endsWith("&")) {
                msg = msg.substring(0, msg.length() - 1);
            }
            return msg;
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    public Map<String, String> getParams(EndpointRequest netRequest) {
        if (!netRequest.isJsonData()) {
            Map<String, String> map = JsonUtils.javaBeanToMap(netRequest);
            return map;
        }
        return null;
    }


    public static void showJSON(String url, String message) {
        if (!TextUtils.isEmpty(url)) Log.e("@@@@@@---requestUrl", "@@@@@@-url: " + url);
        Log.e("@@@@@@---", "@@@@@@- Begin " + message + " Body -@@@@@@");
    }
}
