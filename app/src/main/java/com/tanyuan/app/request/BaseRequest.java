package com.tanyuan.app.request;

import com.tanyuan.network.request.NetRequest;

import java.util.Set;

import okhttp3.Headers;

/**
 * Created by liuxingxing on 2017/11/16.
 */

public class BaseRequest extends NetRequest {

    private Integer pageSize;

    private Integer pageIndex;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder();
        Headers superHeaders = super.getHeaders();
        for (String key : superHeaders.names()){
            builder.add(key,superHeaders.get(key));
        }
        builder.add("Content-Type","application/x-www-form-urlencoded");
        builder.add("token","f5125e9d1c6542ef86ad51137c641921");
        builder.add("userId","25876475");
        return builder.build();
    }
}
