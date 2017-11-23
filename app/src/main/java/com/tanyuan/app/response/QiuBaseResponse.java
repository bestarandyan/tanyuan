package com.tanyuan.app.response;


/**
 * Created by liuxingxing on 2015/2/9.
 */
public class QiuBaseResponse extends EndpointResponse {
    public boolean succeeded() {
        return getResultCode() == 0;
    }
}
