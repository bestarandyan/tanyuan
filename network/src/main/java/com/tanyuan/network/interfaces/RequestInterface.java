package com.tanyuan.network.interfaces;

import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/12/5.
 */

public interface RequestInterface<T> {
    void onReceivedData(T response);
    void onErrorData(T response);
}
