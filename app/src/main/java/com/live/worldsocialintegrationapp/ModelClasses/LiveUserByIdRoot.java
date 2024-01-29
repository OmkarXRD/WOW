package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveUserByIdRoot {

    public String success;
    public String message;
    public Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public  class  Details{

        public String id;
        public String hostType;
        public String userId;
        public String channelName;
        public String token;
        public String latitude;
        public String longitude;
        public String rtmToken;
        public String status;
        public String archivedDate;
        public String endTime;
        public String liveCount;
        public String password;
//        @JsonProperty("Liveimage")
        public String Liveimage;
        public String imageText;
        public String imageTitle;
        public String created;
        public String bool;
        public String live_hideUnhideStatus;
        public String live_hideUnhideExpTime;
        public String totaltimePerLive;
        public String createdDate;
        public String createdTime;
        public String name;
        public String username;

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

        public String getArchivedDate() {
            return archivedDate;
        }

        public void setArchivedDate(String archivedDate) {
            this.archivedDate = archivedDate;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getLiveCount() {
            return liveCount;
        }

        public void setLiveCount(String liveCount) {
            this.liveCount = liveCount;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getImageTitle() {
            return imageTitle;
        }

        public void setImageTitle(String imageTitle) {
            this.imageTitle = imageTitle;
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

        public String getLive_hideUnhideStatus() {
            return live_hideUnhideStatus;
        }

        public void setLive_hideUnhideStatus(String live_hideUnhideStatus) {
            this.live_hideUnhideStatus = live_hideUnhideStatus;
        }

        public String getLive_hideUnhideExpTime() {
            return live_hideUnhideExpTime;
        }

        public void setLive_hideUnhideExpTime(String live_hideUnhideExpTime) {
            this.live_hideUnhideExpTime = live_hideUnhideExpTime;
        }

        public String getTotaltimePerLive() {
            return totaltimePerLive;
        }

        public void setTotaltimePerLive(String totaltimePerLive) {
            this.totaltimePerLive = totaltimePerLive;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserProfileImage() {
            return UserProfileImage;
        }

        public void setUserProfileImage(String userProfileImage) {
            UserProfileImage = userProfileImage;
        }

        public boolean isKickOutStatus() {
            return kickOutStatus;
        }

        public void setKickOutStatus(boolean kickOutStatus) {
            this.kickOutStatus = kickOutStatus;
        }

        //        @JsonProperty("UserProfileImage")
        public String UserProfileImage;
        public boolean kickOutStatus;
    }
}
