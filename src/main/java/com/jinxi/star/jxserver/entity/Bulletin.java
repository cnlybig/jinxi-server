package com.jinxi.star.jxserver.entity;

import java.io.Serializable;

public class Bulletin implements Serializable {
    private String bulletinId;
    private String bulletinName;
    private String communityName;
    private String img;
    private int[] imgId;

    public String getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getBulletinName() {
        return bulletinName;
    }

    public void setBulletinName(String bulletinName) {
        this.bulletinName = bulletinName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int[] getImgId() {
        return imgId;
    }

    public void setImgId(int[] imgId) {
        this.imgId = imgId;
    }
}
