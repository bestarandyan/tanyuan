package com.tanyuan.app.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liuxingxing on 16/2/23.
 */
public class CircleModel {
    /**
     * 动态id
     */
    private Integer userStateId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 用户头像缩略图
     */
    private String smallAvatar;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 动态类型，1：普通文本，2：图片文本，3：视频文本，4：分享文本(3/4为预留)
     */
    private Integer type;

    private Integer userType;

    /**
     * 动态文字内容
     */
    private String content;

    /**
     * 动态图片缩略图列表
     */
    private List<String> thumbnailImgUrls;


    /**
     * 动态图片列表
     */
    private List<String> imgUrls;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 悬赏数量
     */
    private Integer awardCount;


    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 距离
     */
    private Integer distance;


    /**
     * 发布时间
     */
    private Long createTime;

    /**
     * 出生日期（年纪可通过出生日期算出）
     */
    private Long birthday;

    /**
     * 性别，1：男，2：女
     */
    private Integer gender;

    /**
     * 点赞状态，1:已点赞，2：未点赞
     */
    private Integer likeStatus;

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
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

    public Integer getUserStateId() {
        return userStateId;
    }

    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
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

    public String getSmallAvatar() {
        return smallAvatar;
    }

    public void setSmallAvatar(String smallAvatar) {
        this.smallAvatar = smallAvatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getThumbnailImgUrls() {
        return thumbnailImgUrls;
    }

    public void setThumbnailImgUrls(List<String> thumbnailImgUrls) {
        this.thumbnailImgUrls = thumbnailImgUrls;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
