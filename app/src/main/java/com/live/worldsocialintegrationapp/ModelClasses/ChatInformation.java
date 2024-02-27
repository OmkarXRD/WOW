package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class ChatInformation implements Serializable {

    private  String message;

    private String type;

    private String objectKey;

    private String date;

    private String time;

    private String userId;

    private String name;

    private String profileImage;


    private String otherUserId;

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public ChatInformation(String message, String type, String objectKey, String date, String time,String name,String profileImage) {
//        this.message = message;
//        this.type = type;
//        this.objectKey = objectKey;
//        this.date = date;
//        this.time = time;
//        this.name = name;
//        this.profileImage= profileImage;
//    }

    public ChatInformation(String message, String type, String objectKey, String date, String time,String name,String profileImage,String otherUserId) {
        this.message = message;
        this.type = type;
        this.objectKey = objectKey;
        this.date = date;
        this.time = time;
        this.name = name;
        this.profileImage= profileImage;
        this.otherUserId = otherUserId;
    }

    public ChatInformation() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

