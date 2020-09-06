package com.jinxi.star.jxserver.dao;

import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.WishInfo;
import com.jinxi.star.jxserver.entity.WishListInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface WishDao {
    //列出所有心愿
    Page<WishInfo> listAllWishs();
    //查询某小区的心愿
    Page<WishInfo> listCommunityWishs(@Param("communityId") String communityId);
    //查询已认领但未完成心愿
    Page<WishInfo> listReceivedUnfinWishs();
    //查询未认领心愿
    Page<WishInfo> listUnreceivedWishs();
    //查询已完成心愿
    Page<WishInfo> listFinishedWishs();
    //查询详细心愿
    WishInfo getWish(@Param("wishId") String wishId);
    //列出所有心愿列表信息
    Page<WishListInfo> listAllWishLists();
    //查询单个心愿列表的信息
    WishListInfo getWishList(@Param("wishId") String wishId);
    //查询我的心愿
    Page<WishInfo> listMyWishs(@Param("openId") String openId);
    //确认心愿领取
    void setWishGet(@Param("wishId") String wishId);
    //认领心愿
    void claimWish(@Param("wishListInfo") WishListInfo wishListInfo);
    //确认心愿完成
    void setWishDone(@Param("wishId") String wishId);
    //确认心愿完成
    void setWishListDone(@Param("wishId") String wishId);
    //发布心愿
    void releaseWish(@Param("wishInfo") WishInfo wishInfo);
    //删除心愿
    void deleteWish(@Param("wishId") String wishId);
}
