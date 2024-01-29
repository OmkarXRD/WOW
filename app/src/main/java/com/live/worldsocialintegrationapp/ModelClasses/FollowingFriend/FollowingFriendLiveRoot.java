package com.live.worldsocialintegrationapp.ModelClasses.FollowingFriend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FollowingFriendLiveRoot {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("social_id")
        @Expose
        private String socialId;
        @SerializedName("myExp")
        @Expose
        private String myExp;
        @SerializedName("myRecieveExperience")
        @Expose
        private String myRecieveExperience;
        @SerializedName("vipLevel")
        @Expose
        private String vipLevel;
        @SerializedName("vipFrom")
        @Expose
        private Object vipFrom;
        @SerializedName("vipTo")
        @Expose
        private Object vipTo;
        @SerializedName("myLevel")
        @Expose
        private String myLevel;
        @SerializedName("myCoin")
        @Expose
        private String myCoin;
        @SerializedName("myDiamond")
        @Expose
        private String myDiamond;
        @SerializedName("totalSendDiamond")
        @Expose
        private String totalSendDiamond;
        @SerializedName("myRecievedDiamond")
        @Expose
        private String myRecievedDiamond;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("Country")
        @Expose
        private String country;
        @SerializedName("country_showUnshow")
        @Expose
        private String countryShowUnshow;
        @SerializedName("myFrame")
        @Expose
        private Object myFrame;
        @SerializedName("myLuckyId")
        @Expose
        private Object myLuckyId;
        @SerializedName("email_verified_at")
        @Expose
        private String emailVerifiedAt;
        @SerializedName("is_admin")
        @Expose
        private String isAdmin;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("lang_id")
        @Expose
        private String langId;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("dev_id")
        @Expose
        private String devId;
        @SerializedName("reg_id")
        @Expose
        private String regId;
        @SerializedName("dev_type")
        @Expose
        private String devType;
        @SerializedName("remember_token")
        @Expose
        private String rememberToken;
        @SerializedName("loginOtp")
        @Expose
        private String loginOtp;
        @SerializedName("login_type")
        @Expose
        private String loginType;
        @SerializedName("registerType")
        @Expose
        private String registerType;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("phoneUpOtp")
        @Expose
        private String phoneUpOtp;
        @SerializedName("familyId")
        @Expose
        private String familyId;
        @SerializedName("eventId")
        @Expose
        private String eventId;
        @SerializedName("isEventCreater")
        @Expose
        private String isEventCreater;
        @SerializedName("isEventSubscriber")
        @Expose
        private String isEventSubscriber;
        @SerializedName("isFamilyLeader")
        @Expose
        private String isFamilyLeader;
        @SerializedName("isFamilyMember")
        @Expose
        private String isFamilyMember;
        @SerializedName("imageDp")
        @Expose
        private String imageDp;

        @SerializedName("imageTitle")
        @Expose
        private String imageTitle;

        @SerializedName("kickOutStatus")
        @Expose
        public Boolean kickOutStatus;

        public Boolean getKickOutStatus() {
            return kickOutStatus;
        }

        public void setKickOutStatus(Boolean kickOutStatus) {
            this.kickOutStatus = kickOutStatus;
        }


        public String getImageTitle() {
            return imageTitle;
        }

        public void setImageTitle(String imageTitle) {
            this.imageTitle = imageTitle;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSocialId() {
            return socialId;
        }

        public void setSocialId(String socialId) {
            this.socialId = socialId;
        }

        public String getMyExp() {
            return myExp;
        }

        public void setMyExp(String myExp) {
            this.myExp = myExp;
        }

        public String getMyRecieveExperience() {
            return myRecieveExperience;
        }

        public void setMyRecieveExperience(String myRecieveExperience) {
            this.myRecieveExperience = myRecieveExperience;
        }

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }

        public Object getVipFrom() {
            return vipFrom;
        }

        public void setVipFrom(Object vipFrom) {
            this.vipFrom = vipFrom;
        }

        public Object getVipTo() {
            return vipTo;
        }

        public void setVipTo(Object vipTo) {
            this.vipTo = vipTo;
        }

        public String getMyLevel() {
            return myLevel;
        }

        public void setMyLevel(String myLevel) {
            this.myLevel = myLevel;
        }

        public String getMyCoin() {
            return myCoin;
        }

        public void setMyCoin(String myCoin) {
            this.myCoin = myCoin;
        }

        public String getMyDiamond() {
            return myDiamond;
        }

        public void setMyDiamond(String myDiamond) {
            this.myDiamond = myDiamond;
        }

        public String getTotalSendDiamond() {
            return totalSendDiamond;
        }

        public void setTotalSendDiamond(String totalSendDiamond) {
            this.totalSendDiamond = totalSendDiamond;
        }

        public String getMyRecievedDiamond() {
            return myRecievedDiamond;
        }

        public void setMyRecievedDiamond(String myRecievedDiamond) {
            this.myRecievedDiamond = myRecievedDiamond;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryShowUnshow() {
            return countryShowUnshow;
        }

        public void setCountryShowUnshow(String countryShowUnshow) {
            this.countryShowUnshow = countryShowUnshow;
        }

        public Object getMyFrame() {
            return myFrame;
        }

        public void setMyFrame(Object myFrame) {
            this.myFrame = myFrame;
        }

        public Object getMyLuckyId() {
            return myLuckyId;
        }

        public void setMyLuckyId(Object myLuckyId) {
            this.myLuckyId = myLuckyId;
        }

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(String isAdmin) {
            this.isAdmin = isAdmin;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLangId() {
            return langId;
        }

        public void setLangId(String langId) {
            this.langId = langId;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDevId() {
            return devId;
        }

        public void setDevId(String devId) {
            this.devId = devId;
        }

        public String getRegId() {
            return regId;
        }

        public void setRegId(String regId) {
            this.regId = regId;
        }

        public String getDevType() {
            return devType;
        }

        public void setDevType(String devType) {
            this.devType = devType;
        }

        public String getRememberToken() {
            return rememberToken;
        }

        public void setRememberToken(String rememberToken) {
            this.rememberToken = rememberToken;
        }

        public String getLoginOtp() {
            return loginOtp;
        }

        public void setLoginOtp(String loginOtp) {
            this.loginOtp = loginOtp;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getPhoneUpOtp() {
            return phoneUpOtp;
        }

        public void setPhoneUpOtp(String phoneUpOtp) {
            this.phoneUpOtp = phoneUpOtp;
        }

        public String getFamilyId() {
            return familyId;
        }

        public void setFamilyId(String familyId) {
            this.familyId = familyId;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getIsEventCreater() {
            return isEventCreater;
        }

        public void setIsEventCreater(String isEventCreater) {
            this.isEventCreater = isEventCreater;
        }

        public String getIsEventSubscriber() {
            return isEventSubscriber;
        }

        public void setIsEventSubscriber(String isEventSubscriber) {
            this.isEventSubscriber = isEventSubscriber;
        }

        public String getIsFamilyLeader() {
            return isFamilyLeader;
        }

        public void setIsFamilyLeader(String isFamilyLeader) {
            this.isFamilyLeader = isFamilyLeader;
        }

        public String getIsFamilyMember() {
            return isFamilyMember;
        }

        public void setIsFamilyMember(String isFamilyMember) {
            this.isFamilyMember = isFamilyMember;
        }

        public String getImageDp() {
            return imageDp;
        }

        public void setImageDp(String imageDp) {
            this.imageDp = imageDp;
        }

    }
}
