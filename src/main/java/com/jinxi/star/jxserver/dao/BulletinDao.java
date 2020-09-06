package com.jinxi.star.jxserver.dao;

import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Bulletin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface BulletinDao {
    //列出所有微公开信息
    Page<Bulletin> listAllBulletins();
    //查询单个微公开信息
    Bulletin getBulletinContent(@Param("bulletinId") String bulletinId);
    //发布微公开
    void releaseBulletin(@Param("bulletin") Bulletin bulletin);
    //删除微公开
    void deleteBulletin(@Param("bulletinId") String bulletinId);
}
