package com.jinxi.star.jxserver.controller;


import com.github.pagehelper.Page;

import com.jinxi.star.jxserver.entity.WishInfo;

import com.jinxi.star.jxserver.entity.WishListInfo;
import com.jinxi.star.jxserver.model.common.ApiDataResult;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.model.common.ApiResult;
import com.jinxi.star.jxserver.service.WishService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("wish")
public class WishController {
    @Autowired
    private WishService wishService;


    @PostMapping("takeWish")
    public void setWishGet(HttpServletRequest request,@RequestBody WishInfo wishInfo) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        String wishId = wishInfo.getWishId();
        wishService.takeWish(wishId,openId);
    }


    @PostMapping("setWishDone")
    public void setWishDone(@RequestBody WishInfo wishInfo) {
        String wishId = wishInfo.getWishId();
        wishService.setWishDone(wishId);
    }

    @PostMapping("deleteWish")
    public void deleteWish(@RequestBody WishInfo wishInfo) {
        String wishId = wishInfo.getWishId();
        wishService.deleteWish(wishId);
    }


    @PostMapping("listAllWishs")
    public ApiPageResult listAllWishs(@RequestBody ApiRequest apiRequest){
        Page<WishInfo> wishlist = wishService.listAllWishs(apiRequest);
        return ApiPageResult.succ(wishlist);
    }

    @PostMapping("listCommunityWishs")
    public ApiPageResult listCommunityWishs(@RequestBody ApiRequest apiRequest,String communityId){
        Page<WishInfo> wishlist = wishService.listCommunityWishs(apiRequest,communityId);
        return ApiPageResult.succ(wishlist);
    }

    @PostMapping("listReceivedUnfinWishs")
    public ApiPageResult listReceivedWishs(@RequestBody ApiRequest apiRequest){
        Page<WishInfo> wishlist = wishService.listReceivedUnfinWishs(apiRequest);
        return ApiPageResult.succ(wishlist);
    }

    @PostMapping("listUnreceivedWishs")
    public ApiPageResult listUnreceivedWishs(@RequestBody ApiRequest apiRequest){
        Page<WishInfo> wishlist = wishService.listUnreceivedWishs(apiRequest);
        return ApiPageResult.succ(wishlist);
    }

    @PostMapping("listFinishedWishs")
    public ApiPageResult listFinishedWishs(@RequestBody ApiRequest apiRequest){
        Page<WishInfo> wishlist = wishService.listFinishedWishs(apiRequest);
        return ApiPageResult.succ(wishlist);
    }

    @PostMapping("listMyWishs")
    public ApiPageResult listMyWishs(HttpServletRequest request,@RequestBody ApiRequest apiRequest){
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        Page<WishInfo> wishlist = wishService.listMyWishs(apiRequest,openId);
        return ApiPageResult.succ(wishlist);
    }

    @PostMapping("getWish")
    public ApiResult getWish(@RequestBody WishInfo wishInfo) {
        String wishId = wishInfo.getWishId();
        WishInfo wish = wishService.getWish(wishId);
        return ApiDataResult.succ(wish);
    }

    @PostMapping("listAllWishLists")
    public ApiPageResult listAllWishLists(@RequestBody ApiRequest apiRequest){
        Page<WishListInfo> wishListInfos = wishService.listAllWishLists(apiRequest);
        return ApiPageResult.succ(wishListInfos);
    }

    @PostMapping("getWishList")
    public ApiResult getWishList(@RequestBody WishListInfo wishListInfo) {
        String wishId = wishListInfo.getWishId();
        WishListInfo wishList = wishService.getWishList(wishId);
        return ApiDataResult.succ(wishList);
    }

    @PostMapping("releaseWish")
    public void releaseWish(HttpServletRequest request, @RequestBody WishInfo wishInfo) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        String wishName = wishInfo.getWishName();
        String wishContent = wishInfo.getWishContent();
        int intergral = wishInfo.getIntergral();
        String communityId = wishInfo.getCommunityId();
        wishService.releaseWish(openId, wishName, wishContent,communityId,intergral);
    }

}
