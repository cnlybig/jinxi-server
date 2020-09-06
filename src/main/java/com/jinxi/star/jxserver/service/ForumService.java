package com.jinxi.star.jxserver.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.jinxi.star.jxserver.dao.ForumDao;
import com.jinxi.star.jxserver.entity.Post;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ForumService {
    @Autowired
    private ForumDao forumDao;

    public Page<Post> listPosts(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return forumDao.listPosts();
    }

    public Post getPostContent(String postId){
        Post post = forumDao.getPostContent(postId);
        String postComment = post.getPostComment();
        String[] comment = postComment.split("\\|");
        String img = post.getImg();
        String[] img1 = img.split("\\|");
        int l =img1.length;
        int[] imgId = new int[l];
        for(int i=0; i<l; i++){
            imgId[i] = Integer.parseInt(img1[i]);
        }
        post.setComment(comment);
        post.setImgId(imgId);
        return post;
    }
    public void commentPost(String commentName,String commentedName,String commentContent,String postId){
        if(commentedName !=null)
        {
            commentContent = commentName + ":@"  + commentedName + " "+ commentContent + "|";
        }
        else
        {
            commentContent = commentName + ": " + commentContent+ "|";
        }
        forumDao.commentPost( postId, commentContent);
    }


    public void releasePost(String openId, String postName, String postContent, String postLabel,int[] imgId){
        Post post = new Post();
        post.setOpenId(openId);
        post.setPostName(postName);
        post.setPostContent(postContent);
        post.setPostLabel(postLabel);
        int k = imgId.length;
        String img = "";
        for(int i = 0; i < k; i++ ){
            img = img + imgId[i] + "|";
        }
        post.setImg(img);
        forumDao.releasePost(post);
    }

    public void deletePost(String postId){
        forumDao.deletePost(postId);
    }

    public void topPost(String postId){
        forumDao.topPost(postId);
    }

    public void cancletopPost(String postId){
        forumDao.cancletopPost(postId);
    }
}
