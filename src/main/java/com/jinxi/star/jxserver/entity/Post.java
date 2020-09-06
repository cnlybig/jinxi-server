package com.jinxi.star.jxserver.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private String postId;
    private String openId;
    private String postName;
    private String posterName;
    private String postContent;
    private String postTime;
    private String postLabel;
    private String postComment;
    private String[] comment;
    private String img;
    private int[] imgId;
    private String commentName;
    private String commentedName;
    private String commentContent;
    private int isTop;


    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int top) {
        isTop = top;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostLabel() {
        return postLabel;
    }

    public void setPostLabel(String postLabel) {
        this.postLabel = postLabel;
    }

    public String getPostComment() {
        return postComment;
    }

    public void setPostComment(String postComment) {
        this.postComment = postComment;
    }

    public String[] getComment() {
        return comment;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int[] getImgId() {
        return imgId;
    }

    public void setImgId(int[] imgId) {
        this.imgId = imgId;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentedName() {
        return commentedName;
    }

    public void setCommentedName(String commentedName) {
        this.commentedName = commentedName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
