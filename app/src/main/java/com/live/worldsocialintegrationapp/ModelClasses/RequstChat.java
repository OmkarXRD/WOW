package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

@Keep
public class RequstChat {

    public String from,userId,type,to,toImg,toName,fromImg,fromName,lastMessage,fromDob="",fromGender="",fromFriendStatus="",toFriendStatus="",byUnfriend;

    public String getToImg() {
        return toImg;
    }

    public String getFromDob() {
        return fromDob;
    }

    public void setFromDob(String fromDob) {
        this.fromDob = fromDob;
    }

    public String getFromFriendStatus() {
        return fromFriendStatus;
    }

    public String getToFriendStatus() {
        return toFriendStatus;
    }

    public String getByUnfriend() {
        return byUnfriend;
    }

    public void setByUnfriend(String byUnfriend) {
        this.byUnfriend = byUnfriend;
    }

    public void setToFriendStatus(String toFriendStatus) {
        this.toFriendStatus = toFriendStatus;
    }

    public String getFromGender() {
        return fromGender;
    }

    public void setFromGender(String fromGender) {
        this.fromGender = fromGender;
    }


    public void setFromFriendStatus(String friendStatus) {
        this.fromFriendStatus = fromFriendStatus;
    }

    public void setToImg(String toImg) {
        this.toImg = toImg;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFromImg() {
        return fromImg;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setFromImg(String fromImg) {
        this.fromImg = fromImg;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    //    public RequstChat(String from, String userId, String type, String to,String name,String image) {
//        this.from = from;
//        this.userId = userId;
//        this.type = type;
//        this.to = to;
//        this.name = name;
//        this.image = image;
//    }

    @Keep
public RequstChat(String from, String userId, String type, String to,String toImg,String toName,String fromImg,String fromName,String lastMessage,String fromDob,String fromGender,String fromFriendStatus,String toFriendStatus) {
    this.from = from;
    this.userId = userId;
    this.type = type;
    this.to = to;
    this.toImg= toImg;
    this.toName = toName;
    this.fromImg = fromImg;
    this.fromName = fromName;
    this.fromDob= fromDob;
    this.fromGender = fromGender;
    this.fromFriendStatus= fromFriendStatus;
    this.toFriendStatus = toFriendStatus;
    this.lastMessage = lastMessage;
}

    public RequstChat() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
