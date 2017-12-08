package com.tanyuan.app.request;

import com.tanyuan.network.request.NetRequest;

import okhttp3.Headers;

/**
 * Created by liuxingxing on 2017/11/16.
 */

public class BaseListRequest extends GetDataRequest {

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

}
