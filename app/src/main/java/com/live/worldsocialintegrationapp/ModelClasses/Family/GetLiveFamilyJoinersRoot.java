package com.live.worldsocialintegrationapp.ModelClasses.Family;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetLiveFamilyJoinersRoot implements Serializable {

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

    public class Detail {

        @SerializedName("familyId")
        @Expose
        public String familyId;
        @SerializedName("uId")
        @Expose
        public String uId;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("hostType")
        @Expose
        public String hostType;
        @SerializedName("userId")
        @Expose
        public String userId;
        @SerializedName("channelName")
        @Expose
        public String channelName;
        @SerializedName("token")
        @Expose
        public String token;
        @SerializedName("latitude")
        @Expose
        public String latitude;
        @SerializedName("longitude")
        @Expose
        public String longitude;
        @SerializedName("rtmToken")
        @Expose
        public String rtmToken;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("archivedDate")
        @Expose
        public String archivedDate;
        @SerializedName("endTime")
        @Expose
        public String endTime;
        @SerializedName("liveCount")
        @Expose
        public String liveCount;
        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("Liveimage")
        @Expose
        public String liveimage;
        @SerializedName("imageText")
        @Expose
        public String imageText;
        @SerializedName("imageTitle")
        @Expose
        public String imageTitle;
        @SerializedName("created")
        @Expose
        public String created;
        @SerializedName("bool")
        @Expose
        public String bool;
        @SerializedName("live_hideUnhideStatus")
        @Expose
        public String liveHideUnhideStatus;
        @SerializedName("live_hideUnhideExpTime")
        @Expose
        public String liveHideUnhideExpTime;
        @SerializedName("totaltimePerLive")
        @Expose
        public String totaltimePerLive;
        @SerializedName("createdDate")
        @Expose
        public String createdDate;
        @SerializedName("createdTime")
        @Expose
        public String createdTime;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("followStatus")
        @Expose
        public Boolean followStatus;
        @SerializedName("imageDp")
        @Expose
        public String imageDp;
        @SerializedName("kickOutStatus")
        @Expose
        public Boolean kickOutStatus;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("user_gender")
        @Expose
        private String userGender;
        @SerializedName("user_dob")
        @Expose
        private String userDob;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public String getUserDob() {
            return userDob;
        }

        public void setUserDob(String userDob) {
            this.userDob = userDob;
        }

        public String getFamilyId() {
            return familyId;
        }

        public void setFamilyId(String familyId) {
            this.familyId = familyId;
        }

        public String getuId() {
            return uId;
        }

        public void setuId(String uId) {
            this.uId = uId;
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
            return liveimage;
        }

        public void setLiveimage(String liveimage) {
            this.liveimage = liveimage;
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

        public String getLiveHideUnhideStatus() {
            return liveHideUnhideStatus;
        }

        public void setLiveHideUnhideStatus(String liveHideUnhideStatus) {
            this.liveHideUnhideStatus = liveHideUnhideStatus;
        }

        public String getLiveHideUnhideExpTime() {
            return liveHideUnhideExpTime;
        }

        public void setLiveHideUnhideExpTime(String liveHideUnhideExpTime) {
            this.liveHideUnhideExpTime = liveHideUnhideExpTime;
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

        public Boolean getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(Boolean followStatus) {
            this.followStatus = followStatus;
        }

        public String getImageDp() {
            return imageDp;
        }

        public void setImageDp(String imageDp) {
            this.imageDp = imageDp;
        }

        public Boolean getKickOutStatus() {
            return kickOutStatus;
        }

        public void setKickOutStatus(Boolean kickOutStatus) {
            this.kickOutStatus = kickOutStatus;
        }
    }
}
