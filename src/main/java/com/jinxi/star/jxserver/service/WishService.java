package com.jinxi.star.jxserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxi.star.jxserver.dao.WishDao;
import com.jinxi.star.jxserver.entity.WishListInfo;
import com.jinxi.star.jxserver.entity.WishInfo;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    @Autowired
    private WishDao wishDao;

    public void takeWish(String wishId,String openId){
        wishDao.setWishGet(wishId);
        WishListInfo wishListInfo = new WishListInfo();
        wishListInfo.setOpenId(openId);
        wishListInfo.setWishId(wishId);
        wishListInfo.setIsComplete(0);
        wishDao.claimWish(wishListInfo);
    }


    public void setWishDone(String wishId){
        wishDao.setWishDone(wishId);
        wishDao.setWishListDone(wishId);
    }


    public Page<WishInfo> listAllWishs(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listAllWishs();
    }

    public Page<WishInfo> listCommunityWishs(ApiRequest request,String communityId){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listCommunityWishs(communityId);
    }

    public Page<WishInfo> listReceivedUnfinWishs(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listReceivedUnfinWishs();
    }

    public Page<WishInfo> listUnreceivedWishs(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listUnreceivedWishs();
    }

    public Page<WishInfo> listFinishedWishs(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listFinishedWishs();
    }

    public Page<WishInfo> listMyWishs(ApiRequest request,String openId){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listMyWishs(openId);
    }

    public WishInfo getWish(String wishId) {

        WishInfo wishInfo = wishDao.getWish(wishId);
        return wishInfo;
    }

    public Page<WishListInfo> listAllWishLists(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return wishDao.listAllWishLists();
    }

    public WishListInfo getWishList(String wishId) {

        WishListInfo wishListInfo = wishDao.getWishList(wishId);

        return wishListInfo;
    }

    public void releaseWish(String openId, String wishName, String wishContent,String communityId,int intergral){
        WishInfo wishInfo = new WishInfo();
        wishInfo.setOpenId(openId);
        wishInfo.setWishName(wishName);
        wishInfo.setWishContent(wishContent);
        wishInfo.setCommnuityId(communityId);
        wishInfo.setIntergral(intergral);
        wishDao.releaseWish(wishInfo);
    }

    public void deleteWish(String wishId){
        wishDao.deleteWish(wishId);
    }

}
