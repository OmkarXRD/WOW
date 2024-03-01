package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

@Keep
public class LiveUserHideUnhideRoot {

    public int success;
    public String message;
    public Details details;



    public String getMessage() {
        return message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
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

    public class Details{
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
        public String created;
        public String bool;
        public String live_hideUnhideStatus;
        public String live_hideUnhideExpTime;

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
    }
}
