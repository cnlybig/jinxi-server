package com.jinxi.star.jxserver.entity;

import java.io.Serializable;
import java.util.Date;

public class WishListInfo implements Serializable {
    private String wishId;
    private String openId;
    private int isComplete;
    private String getTime;

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

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }
}
