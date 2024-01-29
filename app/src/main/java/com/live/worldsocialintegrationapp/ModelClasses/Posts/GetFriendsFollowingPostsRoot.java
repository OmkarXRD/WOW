package com.live.worldsocialintegrationapp.ModelClasses.Posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFriendsFollowingPostsRoot {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public class Detail{
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("likeCount")
        @Expose
        private String likeCount;
        @SerializedName("commentCount")
        @Expose
        private String commentCount;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("postCreated")
        @Expose
        private String postCreated;
        @SerializedName("updated")
        @Expose
        private String updated;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("userImage")
        @Expose
        private UserImage userImage;
        @SerializedName("postTime")
        @Expose
        private String postTime;
        @SerializedName("post_likeStatus")
        @Expose
        private Boolean postLikeStatus;
        @SerializedName("commentBy")
        @Expose
        private String commentBy;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("commentByame")
        @Expose
        private String commentByame;
        @SerializedName("commentByUsername")
        @Expose
        private String commentByUsername;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getPostCreated() {
            return postCreated;
        }

        public void setPostCreated(String postCreated) {
            this.postCreated = postCreated;
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

        public UserImage getUserImage() {
            return userImage;
        }

        public void setUserImage(UserImage userImage) {
            this.userImage = userImage;
        }

        public String getPostTime() {
            return postTime;
        }

        public void setPostTime(String postTime) {
            this.postTime = postTime;
        }

        public Boolean getPostLikeStatus() {
            return postLikeStatus;
        }

        public void setPostLikeStatus(Boolean postLikeStatus) {
            this.postLikeStatus = postLikeStatus;
        }

        public String getCommentBy() {
            return commentBy;
        }

        public void setCommentBy(String commentBy) {
            this.commentBy = commentBy;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCommentByame() {
            return commentByame;
        }

        public void setCommentByame(String commentByame) {
            this.commentByame = commentByame;
        }

        public String getCommentByUsername() {
            return commentByUsername;
        }

        public void setCommentByUsername(String commentByUsername) {
            this.commentByUsername = commentByUsername;
        }

    }


}
