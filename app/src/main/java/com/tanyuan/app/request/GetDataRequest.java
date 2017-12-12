package com.tanyuan.app.request;

import android.content.Context;

import com.tanyuan.app.response.LoginEntity;
import com.tanyuan.app.utils.preference.UserPreferenceUtils;
import com.tanyuan.network.request.NetRequest;

import okhttp3.Headers;

/**
 * Created by liuxingxing on 2017/11/16.
 */

public class GetDataRequest extends NetRequest {
    Context mContext;

    GetDataRequest(Context context){
        mContext = context;
    }

    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder();
        Headers superHeaders = super.getHeaders();
        for (String key : superHeaders.names()){
            builder.add(key,superHeaders.get(key));
        }
        LoginEntity entity = UserPreferenceUtils.getUserData(mContext);
        builder.add("Content-Type","application/x-www-form-urlencoded");
        builder.add("token",entity!=null?entity.token:"");
        builder.add("userId",entity!=null?entity.user_id:"");
        return builder.build();
    }
}
