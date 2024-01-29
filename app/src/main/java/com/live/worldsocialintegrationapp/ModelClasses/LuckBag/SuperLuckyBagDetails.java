package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuperLuckyBagDetails {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;

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

    public class Details {

        @SerializedName("superLuckyBagDetailsId")
        @Expose
        private String superLuckyBagDetailsId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("bagCreaterId")
        @Expose
        private String bagCreaterId;
        @SerializedName("liveId")
        @Expose
        private String liveId;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("hostType")
        @Expose
        private String hostType;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("channelName")
        @Expose
        private String channelName;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("rtmToken")
        @Expose
        private String rtmToken;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("archivedDate")
        @Expose
        private String archivedDate;
        @SerializedName("endTime")
        @Expose
        private String endTime;
        @SerializedName("liveCount")
        @Expose
        private String liveCount;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("Liveimage")
        @Expose
        private String liveimage;
        @SerializedName("imageText")
        @Expose
        private String imageText;
        @SerializedName("imageTitle")
        @Expose
        private String imageTitle;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("bool")
        @Expose
        private String bool;
        @SerializedName("live_hideUnhideStatus")
        @Expose
        private String liveHideUnhideStatus;
        @SerializedName("live_hideUnhideExpTime")
        @Expose
        private String liveHideUnhideExpTime;
        @SerializedName("totaltimePerLive")
        @Expose
        private String totaltimePerLive;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("createdTime")
        @Expose
        private String createdTime;
        @SerializedName("host_name")
        @Expose
        private String hostName;
        @SerializedName("host_username")
        @Expose
        private String hostUsername;
        @SerializedName("host_dob")
        @Expose
        private String hostDob;
        @SerializedName("host_gender")
        @Expose
        private String hostGender;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("host_imageDp")
        @Expose
        private String hostImageDp;
        @SerializedName("user_imageDp")
        @Expose
        private String userImageDp;
        @SerializedName("user_dob")
        @Expose
        private String userDob;
        @SerializedName("user_gender")
        @Expose
        private String userGender;
        @SerializedName("create_status")
        @Expose
        private Boolean createStatus;

        public String getSuperLuckyBagDetailsId() {
            return superLuckyBagDetailsId;
        }

        public void setSuperLuckyBagDetailsId(String superLuckyBagDetailsId) {
            this.superLuckyBagDetailsId = superLuckyBagDetailsId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBagCreaterId() {
            return bagCreaterId;
        }

        public void setBagCreaterId(String bagCreaterId) {
            this.bagCreaterId = bagCreaterId;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
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

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getHostUsername() {
            return hostUsername;
        }

        public void setHostUsername(String hostUsername) {
            this.hostUsername = hostUsername;
        }

        public String getHostDob() {
            return hostDob;
        }

        public void setHostDob(String hostDob) {
            this.hostDob = hostDob;
        }

        public String getHostGender() {
            return hostGender;
        }

        public void setHostGender(String hostGender) {
            this.hostGender = hostGender;
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

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHostImageDp() {
            return hostImageDp;
        }

        public void setHostImageDp(String hostImageDp) {
            this.hostImageDp = hostImageDp;
        }

        public String getUserImageDp() {
            return userImageDp;
        }

        public void setUserImageDp(String userImageDp) {
            this.userImageDp = userImageDp;
        }

        public String getUserDob() {
            return userDob;
        }

        public void setUserDob(String userDob) {
            this.userDob = userDob;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public Boolean getCreateStatus() {
            return createStatus;
        }

        public void setCreateStatus(Boolean createStatus) {
            this.createStatus = createStatus;
        }
    }
}
