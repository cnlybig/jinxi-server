package com.jinxi.star.jxserver.dao;

import com.jinxi.star.jxserver.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ActivityDao {

    void insertActivity(@Param("data")Activity activity);
}
