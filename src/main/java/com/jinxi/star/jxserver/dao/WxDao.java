package com.jinxi.star.jxserver.dao;

import com.alibaba.fastjson.JSON;
import com.jinxi.star.jxserver.exception.AppException;
import com.jinxi.star.jxserver.model.weixin.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * 调用微信相关接口dao
 *
 * @author: Shiz
 * @date: 2020/6/30 17:03
 */
@Repository
public class WxDao {
    private static final Logger logger = LoggerFactory.getLogger(WxDao.class);

    private static final String APPID = "wx0576f249838b1e31";
    private static final String SECRET = "4fd373d97474373ba68088364dad95d4";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String WXURL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private RestTemplate restTemplate;

    public Auth getAuth(String code) {
        String url = WXURL + "?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code
                + "&grant_type=" + GRANT_TYPE;

        String res = restTemplate.getForObject(url, String.class);
        Auth auth = JSON.parseObject(res, Auth.class);
        logger.info("wechatLogin 调用微信auth 应答：" + auth);
        if (auth.getErrcode() != 0) {
            throw new AppException(-3, "调用微信小程序接口异常:" + auth.getErrmsg());
        }
        return auth;
    }
}
