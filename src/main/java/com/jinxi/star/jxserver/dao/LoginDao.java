package com.jinxi.star.jxserver.dao;

import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 公共接口 数据调用
 *
 * @author: Shiz
 * @date: 2020/6/30 13:14
 */
@Mapper
@Component
public interface LoginDao {

    int isAdminUser(@Param("userName") String userName,@Param("password")String password);

    /**
     * 根据openId获取用户信息
     *
     * @param openId
     * @return
     */
    LoginInfo getUserByOpenId(@Param("openId") String openId);

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    LoginInfo getUserByUserId(@Param("userId") String userId);

    /**
     * 根据openId更新登陆时间
     * @param openId
     */
    void updateLoginTime(@Param("openId") String openId);

    void register(@Param("loginInfo") LoginInfo loginInfo);

    Page<LoginInfo> listUsers(@Param("userName")String userName);

}
