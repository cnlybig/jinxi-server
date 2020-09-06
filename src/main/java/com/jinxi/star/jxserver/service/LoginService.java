package com.jinxi.star.jxserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxi.star.jxserver.dao.LoginDao;
import com.jinxi.star.jxserver.entity.LoginInfo;
import com.jinxi.star.jxserver.exception.AppException;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.model.sys.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Shiz
 * @date: 2020/6/30 14:08
 */
@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public LoginInfo getUser(String openId) {

        LoginInfo loginInfo = loginDao.getUserByOpenId(openId);
        if (loginInfo != null) {
            //登记登陆时间
            loginDao.updateLoginTime(openId);
        }
        return loginInfo;
    }

    public LoginInfo registerUser(LoginInfo loginInfo) {
        loginDao.register(loginInfo);
        String userId = loginInfo.getUserId();
        return loginDao.getUserByUserId(userId);
    }

    public Page<LoginInfo> listUser(ApiRequest<UserRequest> request) {
        // 分页查询
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        if (request.getData() == null) {
            throw new AppException(-2, "查询用户数据缺少参数");
        }
        return loginDao.listUsers(request.getData().getUserName());
    }
}
