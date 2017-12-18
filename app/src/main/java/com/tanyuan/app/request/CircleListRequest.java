package com.tanyuan.app.request;

import android.content.Context;


import com.tanyuan.network.interfaces.RequestConfig;

import java.math.BigDecimal;

/**
 * Created by liuxingxing on 16/2/23.
 */
@RequestConfig(path = "app/user/state/list")
public class CircleListRequest extends BaseListRequest {

    public CircleListRequest(Context context) {
        super(context);
    }
    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lon;

    private Integer pageIndex;

    private Integer pageSize;

    /**
     * 要查看的球友的用户id，该字段为空时，则表示获取所有球友的动态
     */
    private Integer userId;

    /**
     * 操作人的用户id，即当前客户端操作用户的id
     */
    private Integer opUserId;

    public Integer getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Integer opUserId) {
        this.opUserId = opUserId;
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

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
