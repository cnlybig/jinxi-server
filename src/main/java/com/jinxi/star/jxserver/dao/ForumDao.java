package com.jinxi.star.jxserver.dao;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
@Repository
public interface ForumDao {
    //查询所有帖子
    Page<Post> listPosts();
    //查询单个帖子具体内容
    Post getPostContent(@Param("postId") String postId);
    //发布帖子
    void releasePost(@Param("post") Post post);
    //删除帖子
    void deletePost(@Param("postId") String postId);
    //评论
    void commentPost(@Param("postId") String postId,@Param("commentContent")String commentContent);
    //置顶
    void topPost(@Param("postId") String postId);
    //取消置顶
    void cancletopPost(@Param("postId") String postId);
}

