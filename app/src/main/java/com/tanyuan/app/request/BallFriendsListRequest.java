package com.tanyuan.app.request;

import android.content.Context;

import com.tanyuan.network.interfaces.RequestConfig;

import java.math.BigDecimal;

/**
 * Created by liuxingxing on 2/9/15.
 */
@RequestConfig(path = "app/user/list/nearby")
public class BallFriendsListRequest extends BaseListRequest{

    public BallFriendsListRequest(Context context) {
        super(context);
    }

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户当前位置纬度
     */
    private BigDecimal lat;


    /**
     * 用户当前位置经度
     */
    private BigDecimal lon;


    /**
     * 性别，1：男，2：女，0或者null为全部
     */
    private Integer gender;


    /**
     * 用户类型，1：普通用户，2：教练，0或者null为全部
     */
    private Integer type;

    /**
     * 上次在线时间，单位为分钟，1天，就是24*60，0或者null为全部
     */
    private Integer lastOnLineTime;



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLastOnLineTime() {
        return lastOnLineTime;
    }

    public void setLastOnLineTime(Integer lastOnLineTime) {
        this.lastOnLineTime = lastOnLineTime;
    }


}
