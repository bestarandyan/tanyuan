package com.tanyuan.app.request;

import android.content.Context;

import com.tanyuan.network.interfaces.RequestConfig;

/**
 * Created by liuxingxing on 2/9/15.
 */

@RequestConfig(path = "app/user/state/delete")
public class DeleteDongtaiRequest extends BaseRequest {

    public DeleteDongtaiRequest(Context context) {
        super(context);
    }


    private Integer userId;

    /**
     * 动态id
     */
    private Integer userStateId;

    public Integer getUserStateId() {
        return userStateId;
    }

    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
