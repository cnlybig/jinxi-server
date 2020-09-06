package com.jinxi.star.jxserver.controller;

import com.jinxi.star.jxserver.config.jwt.JwtHelper;
import com.jinxi.star.jxserver.dao.WxDao;
import com.jinxi.star.jxserver.entity.LoginInfo;
import com.jinxi.star.jxserver.model.common.ApiDataResult;
import com.jinxi.star.jxserver.model.common.ApiResult;
import com.jinxi.star.jxserver.model.weixin.Auth;
import com.jinxi.star.jxserver.service.LoginService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Shiz
 * @date: 2020/6/30 11:57
 */
@RestController
@RequestMapping("login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private WxDao wxDao;

    /**
     * 登陆接口
     *
     * @param code
     * @return
     */
    @PostMapping("wechatLogin")
    public ApiDataResult wechatLogin(@RequestBody Map<String,Object> code) {
        //获取小程序给的code,调用微信auth.code2Session,获得open_id
        //Auth auth = wxDao.getAuth((String) code.get("code"));
        //return ApiDataResult.succ(JwtHelper.generateToken(auth.getOpenid(), auth.getUnionid()));
        return ApiDataResult.succ(JwtHelper.generateToken("222","111"));
    }

    @PostMapping("getUser")
    public ApiResult getUser(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        LoginInfo loginInfo = loginService.getUser(openId);
        return ApiDataResult.succ(loginInfo);
    }

    @PostMapping("register")
    public ApiResult register(HttpServletRequest request, @RequestBody LoginInfo loginInfo) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        String unionId = (String) claims.get("unionId");
        loginInfo.setOpenId(openId);
        loginInfo.setUnionId(unionId);
        LoginInfo info = loginService.registerUser(loginInfo);
        return ApiDataResult.succ(info);
    }

}
