package com.jinxi.star.jxserver.controller;


import com.github.pagehelper.Page;
import com.jinxi.star.jxserver.entity.Post;
import com.jinxi.star.jxserver.model.common.ApiPageResult;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import com.jinxi.star.jxserver.service.ForumService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("forum")
public class ForumController {
    @Autowired
    private ForumService forumService;
    @PostMapping("listPosts")
    public ApiPageResult listPosts(@RequestBody ApiRequest apiRequest){
        Page<Post> posts = forumService.listPosts(apiRequest);
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                return p2.getIsTop()-p1.getIsTop();
            }
        });
        return ApiPageResult.succ(posts);
    }

    @PostMapping("getPostContent")
    public Post getPostContent( @RequestBody Post post) {
        String postId = post.getPostId();
        Post post1 = forumService.getPostContent(postId);
        return post1;
    }

    @PostMapping("releasePost")
    public void releasePost( HttpServletRequest request,@RequestBody Post post) {
        Claims claims = (Claims) request.getAttribute("claims");
        String openId = (String) claims.get("openId");
        String postName = post.getPostName();
        String postContent = post.getPostContent();
        String postLabel = post.getPostLabel();
        int[] imgId = post.getImgId();
        forumService.releasePost(openId,postName,postContent,postLabel,imgId);

    }

    @PostMapping("commentPost")
    public void commentPost(@RequestBody Post post) {
        String postId = post.getPostId();
        String commentName = post.getCommentName();
        String commentedName = post.getCommentedName();
        String  commentContent = post.getCommentContent();
        forumService.commentPost(commentName,commentedName,commentContent,postId);
    }

    @PostMapping("deletePost")
    public void deletePost( @RequestBody Post post) {
        String postId = post.getPostId();
        forumService.deletePost(postId);
    }

    @PostMapping("topPost")
    public void topPost( @RequestBody Post post) {
        String postId = post.getPostId();
        forumService.topPost(postId);
    }

    @PostMapping("cancletopPost")
    public void cancletopPost( @RequestBody Post post) {
        String postId = post.getPostId();
        forumService.cancletopPost(postId);
    }
}
