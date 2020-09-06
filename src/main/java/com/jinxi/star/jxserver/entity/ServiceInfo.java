package com.jinxi.star.jxserver.entity;

import java.io.Serializable;


public class ServiceInfo implements Serializable {
    private String serviceId;
    private String serviceName;
    private String serviceContent;
    private int serviceCategory;
    private String openId;
    private String offerName;
    private double servicePrice;
    private String servicePublishTime;
    private String serviceStarTime;
    private String serviceEndTime;
    private String businessLicense;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public int getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(int serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServicePublishTime() {
        return servicePublishTime;
    }

    public void setServicePublishTime(String servicePublishTime) {
        this.servicePublishTime = servicePublishTime;
    }

    public String getServiceStarTime() {
        return serviceStarTime;
    }

    public void setServiceStarTime(String serviceStarTime) {
        this.serviceStarTime = serviceStarTime;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getbusinessLicense() {
        return businessLicense;
    }

    public void setbusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }
}
