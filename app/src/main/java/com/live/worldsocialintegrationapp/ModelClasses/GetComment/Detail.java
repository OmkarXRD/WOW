package com.live.worldsocialintegrationapp.ModelClasses.GetComment;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class Detail implements Serializable {

    public String id;
    public String feedId;
    public String userId;
    public String comment;
    public String created;
    public String updated;
    public String name;
    public String image;
    public String commentCreatedTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getCommentCreatedTime() {
        return commentCreatedTime;
    }

    public void setCommentCreatedTime(String commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
