package com.jinxi.star.jxserver.dao;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.OrderInfo;
import com.jinxi.star.jxserver.entity.ServiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface ServiceDao {
    //查询所有服务
    Page<ServiceInfo> listAllServices();
    //列出管理员发布的服务
    Page<ServiceInfo> listAdminServices();
    //查询我的服务
    Page<ServiceInfo> listMyServices(@Param("openId") String openId);
    //查询有偿服务
    Page<ServiceInfo> listPaidServices();
    //查询无偿服务
    Page<ServiceInfo> listFreeServices();
    //查询单个服务具体内容
    ServiceInfo getServiceContent(@Param("serviceId") String serviceId);
    //下单
    void placeOrder(@Param("orderInfo") OrderInfo orderInfo);
    //个人发布服务
    void releaseService(@Param("serviceInfo") ServiceInfo serviceInfo);
    //删除个人服务
    void deleteService(@Param("serviceId") String serviceId);
    //管理员发布服务
    void adminReleaseService(@Param("serviceInfo") ServiceInfo serviceInfo);
    //管理员删除服务
    void adminDeleteService(@Param("serviceId") String serviceId);
}

