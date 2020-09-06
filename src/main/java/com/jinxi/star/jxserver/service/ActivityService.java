package com.jinxi.star.jxserver.service;

import com.jinxi.star.jxserver.dao.ActivityDao;
import com.jinxi.star.jxserver.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动相关服务
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    public void sub(Activity activity){
        activityDao.insertActivity(activity);
    }
}
