package com.tanyuan.app.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liuxingxing on 15/10/18.
 */
public class BallFriendsModel {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 出生日期（年纪可通过出生日期算出）
     */
    private Long birthday;

    /**
     * 性别，1：男，2：女
     */
    private Integer gender;


    /**
     * 用户类型，1：普通用户，2：教练
     */
    private Integer type;


    /**
     * 动态(签名)
     */
    private String mood;


    /**
     * 头像
     */
    private String avatar;
    /**
     * 头像
     */
    private String smallAvatar;


    /**
     * 距离
     */
    private Integer distance;


    /**
     * 最后在线时间
     */
    private Long lastOnLineTime;


    /**
     * 球友上次在线位置纬度
     */
    private BigDecimal lat;

    /**
     * 球友上次在线位置经度
     */
    private BigDecimal lon;

    /**
     * 接单数量
     */
    private Integer orderNum;

    /**
     * 教练的价格
     */
    private BigDecimal price;

    /**
     * 教练的球类，是一个列表，教练可以选择多个球类
     *	球类，1:桌球,2:羽毛球,3:网球,4:乒乓球
     */
    private List<Integer> ballType;

    public String getSmallAvatar() {
        return smallAvatar;
    }

    public void setSmallAvatar(String smallAvatar) {
        this.smallAvatar = smallAvatar;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Integer> getBallType() {
        return ballType;
    }

    public void setBallType(List<Integer> ballType) {
        this.ballType = ballType;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
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

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Long getLastOnLineTime() {
        return lastOnLineTime;
    }

    public void setLastOnLineTime(Long lastOnLineTime) {
        this.lastOnLineTime = lastOnLineTime;
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
}
