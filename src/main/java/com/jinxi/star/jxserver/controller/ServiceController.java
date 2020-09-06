package com.jinxi.star.jxserver.controller;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.ServiceInfo;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.service.ServiceService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("listAllServices")
    public ApiPageResult listAllServices(@RequestBody ApiRequest apiRequest){
        Page<ServiceInfo> servicelist = serviceService.listAllServices(apiRequest);
        return ApiPageResult.succ(servicelist);
    }

    @PostMapping("listAdminServices")
    public ApiPageResult listAdminServices(@RequestBody ApiRequest apiRequest){
        Page<ServiceInfo> servicelist = serviceService.listAdminServices(apiRequest);
        return ApiPageResult.succ(servicelist);
    }

    @PostMapping("listFreeServices")
    public ApiPageResult listFreeServices(@RequestBody ApiRequest apiRequest){
        Page<ServiceInfo> servicelist = serviceService.listFreeServices(apiRequest);
        return ApiPageResult.succ(servicelist);
    }

    @PostMapping("listPaidServices")
    public ApiPageResult listPaidServices(@RequestBody ApiRequest apiRequest){
        Page<ServiceInfo> servicelist = serviceService.listPaidServices(apiRequest);
        return ApiPageResult.succ(servicelist);
    }

    @PostMapping("listMyServices")
    public ApiPageResult listMyServices(HttpServletRequest request,@RequestBody ApiRequest apiRequest){
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        Page<ServiceInfo> servicelist = serviceService.listMyServices(apiRequest,openId);
        return ApiPageResult.succ(servicelist);
    }

    @PostMapping("getServiceContent")
    public ServiceInfo getServiceContent( @RequestBody ServiceInfo serviceInfo) {
        String serviceId = serviceInfo.getServiceId();
        ServiceInfo service = serviceService.getServiceContent(serviceId);
        return service;
    }

    @PostMapping("placeOrder")
    public void placeOrder(HttpServletRequest request, @RequestBody ServiceInfo serviceInfo) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        String serviceId = serviceInfo.getServiceId();
        serviceService.placeOrder(serviceId, openId);
    }

    @PostMapping("releaseService")
    public void releaseService( HttpServletRequest request,@RequestBody ServiceInfo serviceInfo) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        String serviceName = serviceInfo.getServiceName();
        String serviceContent = serviceInfo.getServiceContent();
        int serviceCategory = serviceInfo.getServiceCategory();
        double servicePrice = serviceInfo.getServicePrice();
        String serviceStartime = serviceInfo.getServiceStarTime();
        String serviceEndtime = serviceInfo.getServiceEndTime();
        serviceService.releaseService(openId,serviceName,serviceContent,serviceCategory,servicePrice,serviceStartime,serviceEndtime);
    }

    @PostMapping("adminReleaseService")
    public void adminReleaseService(@RequestBody ServiceInfo serviceInfo) {
        String serviceName = serviceInfo.getServiceName();
        String serviceContent = serviceInfo.getServiceContent();
        int serviceCategory = serviceInfo.getServiceCategory();
        double servicePrice = serviceInfo.getServicePrice();
        String serviceStartime = serviceInfo.getServiceStarTime();
        String serviceEndtime = serviceInfo.getServiceEndTime();
        serviceService.adminReleaseService(serviceName,serviceContent,serviceCategory,servicePrice,serviceStartime,serviceEndtime);
    }

    @PostMapping("deleteService")
    public void deleteService( @RequestBody ServiceInfo serviceInfo) {
        String serviceId = serviceInfo.getServiceId();
        serviceService.deleteService(serviceId);
    }

    @PostMapping("adminDeleteService")
    public void adminDeleteService( @RequestBody ServiceInfo serviceInfo) {
        String serviceId = serviceInfo.getServiceId();
        serviceService.adminDeleteService(serviceId);
    }
}
