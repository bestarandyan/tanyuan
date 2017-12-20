package com.tanyuan.network.request;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;

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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by liuxingxing on 2017/11/16.
 */

public class RequestManager<T> {

    public RequestInterface requestInterface;
    public Class<T> clazz;

    public static RequestManager builder() {
        return new RequestManager();
    }

    /**
     * 获取请求服务器请求地址
     * @param netRequest
     * @return
     */
    private String getUrl(EndpointRequest netRequest) {
        StringBuffer httpUrl = new StringBuffer();
        String host = BuildConfig.API_HOST;
        httpUrl.append(host);
        RequestConfig config = netRequest.getClass().getAnnotation(RequestConfig.class);
        if (config == null) return "";
        String path = config.path();
        if (!TextUtils.isEmpty(path)) {
            httpUrl.append("/");
            httpUrl.append(path);
        }
        Log.e("请求链接=========", httpUrl.toString());
        return httpUrl.toString();
    }

    /**
     * 获取get请求的请求地址
     * @param netRequest
     * @return
     */
    private String getUrlForGetRequest(EndpointRequest netRequest){
        StringBuffer httpUrl = new StringBuffer();
        httpUrl.append(getUrl(netRequest));
        String content = encodeParametersToString(getParams(netRequest));
        if (!TextUtils.isEmpty(content)) {
            httpUrl.append("?");
            httpUrl.append(content);
        }
        Log.e("get请求链接=========", httpUrl.toString());
        return httpUrl.toString();
    }

    /**
     * get请求
     * @param netRequest
     * @return
     */
    public RequestManager requestByGet(EndpointRequest netRequest) {
        final Request request = new Request.Builder()
                .url(getUrlForGetRequest(netRequest))
                .headers(netRequest.getHeaders())
                .build();
        sendRequest(request);
        return this;
    }

    /**
     * post请求
     * @param netRequest
     * @return
     */
    public RequestManager requestByPost(EndpointRequest netRequest) {
        RequestBody body = /*getRequestBody(netRequest);*/getBuilder(netRequest).build();
        String url = getUrl(netRequest);
        Log.e("请求头=========", netRequest.getHeaders().toString());
        Request request = new Request.Builder()
                .url(url)
                .headers(netRequest.getHeaders())
//                .tag(netRequest)
                .post(body)
                .build();
        sendRequest(request);
        return this;
    }

    /**
     * 发送请求  并设置监听器
     * @param request
     */
    private void sendRequest(Request request){
        Call call = getClient().newCall(request);
        call.enqueue(new RequestCallBack(requestInterface, clazz));
    }

    /**
     *初始化OkHttpClient
     * @return
     */
    private OkHttpClient getClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
//                .addInterceptor(new BodyInterceptor(new ObjectMapper()))
                .build();
        return okHttpClient;
    }


    public RequestManager setRequestListener(RequestInterface<T> responseInterface) {
        this.requestInterface = responseInterface;
        return this;
    }

    public RequestManager setResponse(Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }


    /**
     * 通过反射机制获取Builder
     *
     * @param netRequest
     * @return
     */
    private FormBody.Builder getBuilder(EndpointRequest netRequest) {
        FormBody.Builder builder = new FormBody.Builder();
        try {
            Method[] methods = netRequest.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    Object value = method.invoke(netRequest, (Object[]) null);
                    builder.add(field.toLowerCase(), null != value ? value.toString() : "");
                }
            }
            Log.e("请求参数=========", builder.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * 通过json方式获取Builder
     *
     * @param netRequest
     * @return
     */
    private FormBody.Builder getBuilderByJson(EndpointRequest netRequest) {
        FormBody.Builder builder = new FormBody.Builder();
        ObjectMapper mMapper = new ObjectMapper();
        mMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String bodyStr;
        try {
            bodyStr = mMapper.writeValueAsString(netRequest);
            JSONObject data = new JSONObject(bodyStr.trim());
            for (int i = 0; i < data.names().length(); i++) {
                builder.add(data.names().getString(i), data.getString(data.names().getString(i)));
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return builder;
    }

    private RequestBody getRequestBody(EndpointRequest netRequest){
        RequestBody requestBody = null;
        ObjectMapper mMapper = new ObjectMapper();
        mMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String bodyStr;
        try {
            bodyStr = mMapper.writeValueAsString(netRequest);
            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),bodyStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return requestBody;
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
                encodedParams.append(URLEncoder.encode(entry.getKey().toLowerCase(), paramsEncoding));
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
