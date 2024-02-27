package com.live.worldsocialintegrationapp.ModelClasses.Posts;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
public class UserImage {

//
//    @SerializedName("id")
//    @Expose
//    private String id;
//    @SerializedName("userId")
//    @Expose
//    private String userId;
//    @SerializedName("image")
//    @Expose
//    private String image;
//    @SerializedName("created")
//    @Expose
//    private String created;
//    @SerializedName("createdTime")
//    @Expose
//    private String createdTime;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getCreated() {
//        return created;
//    }
//
//    public void setCreated(String created) {
//        this.created = created;
//    }
//
//    public String getCreatedTime() {
//        return createdTime;
//    }
//
//    public void setCreatedTime(String createdTime) {
//        this.createdTime = createdTime;
//    }


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
