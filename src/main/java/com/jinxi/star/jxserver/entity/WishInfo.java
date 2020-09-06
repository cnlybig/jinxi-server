package com.jinxi.star.jxserver.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class WishInfo implements Serializable {
    private String wishId;
    private String communityId;
    private String communityName;
    private String wishName;
    private String wishContent;
    private String openId;
    private String recipientName;
    private String isAudit;
    private int isComplete;
    private int intergral;
    private String wishPublishTime;

    public String getWishId() {
        return wishId;
    }

    public void setWishId(String wishId) {
        this.wishId = wishId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setCommnuityId(String commnuityId) {
        this.communityId = commnuityId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public int getIntergral() {
        return intergral;
    }

    public void setIntergral(int intergral) {
        this.intergral = intergral;
    }

    public String getWishPublishTime() {
        return wishPublishTime;
    }

    public void setWishPublishTime(String wishPublishTime) {
        this.wishPublishTime = wishPublishTime;
    }

}
