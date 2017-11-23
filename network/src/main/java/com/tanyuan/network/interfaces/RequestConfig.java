package com.tanyuan.network.interfaces;

/**
 * Created by liuxingxing on 2017/11/23.
 */

public @interface RequestConfig {
    String path() default "";
    int container() default -1;

}
