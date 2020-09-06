package com.jinxi.star.jxserver.entity;

import java.io.Serializable;

public class Minority implements Serializable {
    private String minorityId;
    private String minorityName;
    private String communityId;
    private String communityName;
    private String img;
    private int[] imgId;

    public String getMinorityId() {
        return minorityId;
    }

    public void setMinorityId(String minorityId) {
        this.minorityId = minorityId;
    }

    public String getMinorityName() {
        return minorityName;
    }

    public void setMinorityName(String minorityName) {
        this.minorityName = minorityName;
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
