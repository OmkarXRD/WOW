package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFollowingLiveRoot {

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

        @SerializedName("userLiveId")
        @Expose
        public String userLiveId;
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
        @SerializedName("LiveLat")
        @Expose
        public String liveLat;
        @SerializedName("LiveLong")
        @Expose
        public String liveLong;
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
        @SerializedName("LivePassword")
        @Expose
        public String livePassword;
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
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("social_id")
        @Expose
        public String socialId;
        @SerializedName("myExp")
        @Expose
        public String myExp;
        @SerializedName("myRecieveExperience")
        @Expose
        public String myRecieveExperience;
        @SerializedName("vipLevel")
        @Expose
        public String vipLevel;
        @SerializedName("vipFrom")
        @Expose
        public String vipFrom;
        @SerializedName("vipTo")
        @Expose
        public String vipTo;
        @SerializedName("myLevel")
        @Expose
        public String myLevel;
        @SerializedName("myCoin")
        @Expose
        public String myCoin;
        @SerializedName("myDiamond")
        @Expose
        public String myDiamond;
        @SerializedName("totalSendDiamond")
        @Expose
        public String totalSendDiamond;
        @SerializedName("myRecievedDiamond")
        @Expose
        public String myRecievedDiamond;
        @SerializedName("dob")
        @Expose
        public String dob;
        @SerializedName("bio")
        @Expose
        public String bio;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("Country")
        @Expose
        public String country;
        @SerializedName("country_showUnshow")
        @Expose
        public String countryShowUnshow;
        @SerializedName("myFrame")
        @Expose
        public String myFrame;
        @SerializedName("myLuckyId")
        @Expose
        public String myLuckyId;
        @SerializedName("email_verified_at")
        @Expose
        public String emailVerifiedAt;
        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("is_admin")
        @Expose
        public String isAdmin;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("lang_id")
        @Expose
        public String langId;
        @SerializedName("age")
        @Expose
        public String age;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("dev_id")
        @Expose
        public String devId;
        @SerializedName("reg_id")
        @Expose
        public String regId;
        @SerializedName("latitude")
        @Expose
        public String latitude;
        @SerializedName("longitude")
        @Expose
        public String longitude;
        @SerializedName("dev_type")
        @Expose
        public String devType;
        @SerializedName("remember_token")
        @Expose
        public String rememberToken;
        @SerializedName("loginOtp")
        @Expose
        public String loginOtp;
        @SerializedName("login_type")
        @Expose
        public String loginType;
        @SerializedName("registerType")
        @Expose
        public String registerType;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("phoneUpOtp")
        @Expose
        public String phoneUpOtp;
        @SerializedName("familyId")
        @Expose
        public String familyId;
        @SerializedName("eventId")
        @Expose
        public String eventId;
        @SerializedName("isEventCreater")
        @Expose
        public String isEventCreater;
        @SerializedName("isEventSubscriber")
        @Expose
        public String isEventSubscriber;
        @SerializedName("isFamilyLeader")
        @Expose
        public String isFamilyLeader;
        @SerializedName("isFamilyMember")
        @Expose
        public String isFamilyMember;
        @SerializedName("monthlyCoins")
        @Expose
        public String monthlyCoins;
        @SerializedName("imageDp")
        @Expose
        public String imageDp;
        @SerializedName("kickOutStatus")
        @Expose
        public Boolean kickOutStatus;

        public String getUserLiveId() {
            return userLiveId;
        }

        public void setUserLiveId(String userLiveId) {
            this.userLiveId = userLiveId;
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

        public String getLiveLat() {
            return liveLat;
        }

        public void setLiveLat(String liveLat) {
            this.liveLat = liveLat;
        }

        public String getLiveLong() {
            return liveLong;
        }

        public void setLiveLong(String liveLong) {
            this.liveLong = liveLong;
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

        public String getLivePassword() {
            return livePassword;
        }

        public void setLivePassword(String livePassword) {
            this.livePassword = livePassword;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getVipFrom() {
            return vipFrom;
        }

        public void setVipFrom(String vipFrom) {
            this.vipFrom = vipFrom;
        }

        public String getVipTo() {
            return vipTo;
        }

        public void setVipTo(String vipTo) {
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

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
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

        public String getMyFrame() {
            return myFrame;
        }

        public void setMyFrame(String myFrame) {
            this.myFrame = myFrame;
        }

        public String getMyLuckyId() {
            return myLuckyId;
        }

        public void setMyLuckyId(String myLuckyId) {
            this.myLuckyId = myLuckyId;
        }

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getMonthlyCoins() {
            return monthlyCoins;
        }

        public void setMonthlyCoins(String monthlyCoins) {
            this.monthlyCoins = monthlyCoins;
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
