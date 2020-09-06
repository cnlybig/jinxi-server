package com.jinxi.star.jxserver.controller;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Tongzhan;
import com.jinxi.star.jxserver.entity.WishInfo;
import com.jinxi.star.jxserver.model.common.ApiDataResult;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.model.common.ApiResult;
import com.jinxi.star.jxserver.service.TongzhanService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("tongzhan")
public class TongzhanController {
    @Autowired
    private TongzhanService tongzhanService;

    @GetMapping("listAllTongzhans")
    public ApiPageResult listAllTongzhans(@RequestBody ApiRequest apiRequest){
        Page<Tongzhan> tongzhans = tongzhanService.listAllTongzhans(apiRequest);
        return ApiPageResult.succ(tongzhans);
    }

    @PostMapping("getTongzhanContent")
    public ApiResult getTongzhanContent(@RequestBody Tongzhan tongzhan) {
        String tongzhanId = tongzhan.getTongZhanId();
        Tongzhan tongzhan1 = tongzhanService.getTongzhanContent(tongzhanId);
        return ApiDataResult.succ(tongzhan1);
    }

    @PostMapping("releaseTongzhan")
    public void releaseTongzhan(@RequestBody Tongzhan tongzhan) {
        String tongZhanName = tongzhan.getTongZhanName();
        String communityId = tongzhan.getCommunityId();
        int[]  imgId = tongzhan.getImgId();
        tongzhanService.releaseTongzhan(tongZhanName,communityId,imgId);
    }

    @PostMapping("deleteTongzhan")
    public void deleteTongzhan(@RequestBody Tongzhan tongzhan) {
        String tongZhanId = tongzhan.getTongZhanId();
        tongzhanService.deleteTongzhan(tongZhanId);
    }

}
