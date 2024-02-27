package com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class Detail implements Serializable {

    public String id;
    public String hostType;
    public String userId;
    public String channelName;
    public String token;
    public String latitude;
    public String longitude;
    public String rtmToken;
    public String status;
    public Object archivedDate;
    public String liveCount;
    public String created;
    public String bool;
    public String image;
    public String name;

    public String userLive_Password;
    public String Liveimage;
    public String imageText;

    public String getUserLive_Password() {
        return userLive_Password;
    }

    public void setUserLive_Password(String userLive_Password) {
        this.userLive_Password = userLive_Password;
    }

    public String getLiveimage() {
        return Liveimage;
    }

    public void setLiveimage(String liveimage) {
        Liveimage = liveimage;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostType() {
        return hostType;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRtmToken() {
        return rtmToken;
    }

    public void setRtmToken(String rtmToken) {
        this.rtmToken = rtmToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(Object archivedDate) {
        this.archivedDate = archivedDate;
    }

    public String getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(String liveCount) {
        this.liveCount = liveCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
