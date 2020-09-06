package com.jinxi.star.jxserver.controller;

import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Activity;
import com.jinxi.star.jxserver.entity.LoginInfo;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.model.common.ApiResult;
import com.jinxi.star.jxserver.model.sys.UserRequest;
import com.jinxi.star.jxserver.service.ActivityService;
import com.jinxi.star.jxserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Shiz
 * @date: 2020/6/30 16:35
 */
@RestController
@RequestMapping("api")
public class VolactivityController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ActivityService activityService;

    @PostMapping("listUsers")
    public ApiPageResult listUsers(HttpServletRequest httpRequest, @RequestBody ApiRequest<UserRequest> apiRequest) {
        Page<LoginInfo> users = loginService.listUser(apiRequest);
        return ApiPageResult.succ(users);
    }

    @PostMapping("subActivity")
    public ApiResult subActivity(HttpServletRequest httpRequest, @RequestBody ApiRequest<Activity> apiResult){
        activityService.sub(apiResult.getData());
        return ApiResult.succ();
    }

}
