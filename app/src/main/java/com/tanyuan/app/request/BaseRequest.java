package com.tanyuan.app.request;

import android.content.Context;

import com.tanyuan.network.request.NetRequest;

import java.util.Set;

import okhttp3.Headers;

/**
 * Created by liuxingxing on 2017/11/16.
 * 非列表数据请求，即单条数据请求
 */

public class BaseRequest extends GetDataRequest {
    public BaseRequest(Context context){
        super(context);
    }

}
