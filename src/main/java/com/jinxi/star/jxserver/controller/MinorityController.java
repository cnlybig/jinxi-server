package com.jinxi.star.jxserver.controller;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Minority;
import com.jinxi.star.jxserver.entity.Tongzhan;
import com.jinxi.star.jxserver.model.common.ApiDataResult;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.model.common.ApiResult;
import com.jinxi.star.jxserver.service.MinorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("minority")
public class MinorityController {
    @Autowired
    private MinorityService minorityService;

    @PostMapping("listAllMinoritys")
    public ApiPageResult listAllMinoritys(@RequestBody ApiRequest apiRequest){
        Page<Minority> minorities = minorityService.listAllMinoritys(apiRequest);
        return ApiPageResult.succ(minorities);
    }

    @PostMapping("getMinorityContent")
    public ApiResult getMinorityContent(@RequestBody Minority minority) {
        String minorityId = minority.getMinorityId();
        Minority minority1 = minorityService.getMinorityContent(minorityId);
        return ApiDataResult.succ(minority1);
    }

    @PostMapping("releaseMinority")
    public void releaseMinority(@RequestBody Minority minority) {
        String minorityName = minority.getMinorityName();
        String communityId = minority.getCommunityId();
        int[] imgId = minority.getImgId();
        minorityService.releaseMinority(minorityName,communityId,imgId);
    }

    @PostMapping("deleteMinority")
    public void deleteMinority(@RequestBody Minority minority) {
        String minorityId = minority.getMinorityId();
        minorityService.deleteMinority(minorityId);
    }

}
