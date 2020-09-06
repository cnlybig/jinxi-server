package com.jinxi.star.jxserver.dao;

import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Minority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface MinorityDao {
    //列出所有少数民族栏信息
    Page<Minority> listAllMinoritys();
    //查询单个少数民族栏信息
    Minority getMinorityContent(@Param("minorityId") String minorityId);
    //发布关于少数民族的信息
    void releaseMinority(@Param("minority") Minority minority);
    //删除某个少数名族栏内容
    void deleteMinority(@Param("minorityId") String minorityId);
}
