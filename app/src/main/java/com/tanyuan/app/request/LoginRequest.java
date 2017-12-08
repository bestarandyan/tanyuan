package com.tanyuan.app.request;

import android.content.Context;

import com.tanyuan.network.interfaces.RequestConfig;

import java.math.BigDecimal;

/**
 * Created by liuxingxing on 2/9/15.
 */
@RequestConfig(path = "user/login")
public class LoginRequest extends BaseRequest{

    public LoginRequest(Context context) {
    }
    public String type;
    public String phone;
    public String password;
    public String open_id;
    public String platform;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
