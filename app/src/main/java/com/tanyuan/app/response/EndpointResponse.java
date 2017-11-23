package com.tanyuan.app.response;

/**
 * Created by wangying on 2015/2/9.
 */
public class EndpointResponse {
    private int resultCode;
    private String message;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
