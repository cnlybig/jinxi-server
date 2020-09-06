package com.jinxi.star.jxserver.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxi.star.jxserver.dao.ServiceDao;
import com.jinxi.star.jxserver.entity.OrderInfo;
import com.jinxi.star.jxserver.entity.ServiceInfo;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceService {
    @Autowired
    private ServiceDao serviceDao;

    public Page<ServiceInfo> listAllServices(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return serviceDao.listAllServices();
    }

    public Page<ServiceInfo> listAdminServices(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return serviceDao.listAdminServices();
    }

    public Page<ServiceInfo> listFreeServices(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return serviceDao.listFreeServices();
    }

    public Page<ServiceInfo> listPaidServices(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return serviceDao.listPaidServices();
    }

    public Page<ServiceInfo> listMyServices(ApiRequest request,String openId){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return serviceDao.listMyServices(openId);
    }

    public ServiceInfo getServiceContent(String serviceId){
        ServiceInfo service = serviceDao.getServiceContent(serviceId);
        return service;
    }

    public void placeOrder(String serviceId, String openId){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOpenId(openId);
        orderInfo.setServiceId(serviceId);
        orderInfo.setIsComplete(0);
        serviceDao.placeOrder(orderInfo);
    }

    public void releaseService(String openId, String serviceName, String serviceContent, int serviceCategory, double servicePrice, String serviceStartime, String serviceEndtime){
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setOpenId(openId);
        serviceInfo.setServiceName(serviceName);
        serviceInfo.setServiceContent(serviceContent);
        serviceInfo.setServiceCategory(serviceCategory);
        serviceInfo.setServicePrice(servicePrice);
        serviceInfo.setServiceStarTime(serviceStartime);
        serviceInfo.setServiceEndTime(serviceEndtime);
        serviceDao.releaseService(serviceInfo);
    }

    public void adminReleaseService(String serviceName, String serviceContent, int serviceCategory, double servicePrice, String serviceStartime, String serviceEndtime){
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setOpenId("001");
        serviceInfo.setServiceName(serviceName);
        serviceInfo.setServiceContent(serviceContent);
        serviceInfo.setServiceCategory(serviceCategory);
        serviceInfo.setServicePrice(servicePrice);
        serviceInfo.setServiceStarTime(serviceStartime);
        serviceInfo.setServiceEndTime(serviceEndtime);
        serviceDao.adminReleaseService(serviceInfo);
    }

    public void deleteService(String serviceId){
        serviceDao.deleteService(serviceId);
    }

    public void adminDeleteService(String serviceId){
        serviceDao.adminDeleteService(serviceId);
    }
}
