package com.jinxi.star.jxserver.entity;

import java.io.Serializable;

public class Tongzhan implements Serializable {
    private String tongZhanId;
    private String tongZhanName;
    private String communityId;
    private String communityName;
    private String img;
    private int[] imgId;

    public String getTongZhanId() {
        return tongZhanId;
    }

    public void setTongZhanId(String tongZhanId) {
        this.tongZhanId = tongZhanId;
    }

    public String getTongZhanName() {
        return tongZhanName;
    }

    public void setTongZhanName(String tongZhanName) {
        this.tongZhanName = tongZhanName;
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
