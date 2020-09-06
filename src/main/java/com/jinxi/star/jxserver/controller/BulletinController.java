package com.jinxi.star.jxserver.controller;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Bulletin;
import com.jinxi.star.jxserver.model.common.ApiDataResult;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.model.common.ApiResult;
import com.jinxi.star.jxserver.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("bulletin")
public class BulletinController {
    @Autowired
    private BulletinService bulletinService;

    @PostMapping("listAllBulletins")
    public ApiPageResult listAllBulletins(@RequestBody ApiRequest apiRequest){
        Page<Bulletin> bulletins = bulletinService.listAllBulletins(apiRequest);
        return ApiPageResult.succ(bulletins);
    }

    @PostMapping("getBulletinContent")
    public ApiResult getBulletinContent(@RequestBody Bulletin bulletin) {
        String bulletinId = bulletin.getBulletinId();
        Bulletin bulletin1 = bulletinService.getBulletinContent(bulletinId);
        return ApiDataResult.succ(bulletin1);
    }

    @PostMapping("releaseBulletin")
    public void releaseBulletin(@RequestBody Bulletin bulletin) {
        String bulletinName = bulletin.getBulletinName();
        String communityName = bulletin.getCommunityName();
        int[] imgId = bulletin.getImgId();
        bulletinService.releaseBulletin(bulletinName,communityName,imgId);
    }

    @PostMapping("deleteBulletin")
    public void deleteBulletin(@RequestBody Bulletin bulletin) {
        String bulletinId = bulletin.getBulletinId();
        bulletinService.deleteBulletin(bulletinId);
    }

}
