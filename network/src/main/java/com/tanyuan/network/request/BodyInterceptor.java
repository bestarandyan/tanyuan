package com.tanyuan.network.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyuan.network.interfaces.EndpointRequest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/12/13.
 */

public class BodyInterceptor implements Interceptor {
    ObjectMapper mapper;
    BodyInterceptor(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    private RequestBody getBody(EndpointRequest request){
        try {
            String body = mapper.writer().writeValueAsString(request);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
