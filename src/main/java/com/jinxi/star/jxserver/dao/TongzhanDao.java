package com.jinxi.star.jxserver.dao;

import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Tongzhan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface TongzhanDao {
    //列出所有统战信息
    Page<Tongzhan> listAllTongzhans();
    //查询单个统战信息
    Tongzhan getTongzhanContent(@Param("tongZhanId") String tongZhanId);
    //发布统战
    void releaseTongzhan(@Param("tongzhan") Tongzhan tongzhan);
    //删除统战
    void deleteTongzhan(@Param("tongZhanId") String tongZhanId);
}
