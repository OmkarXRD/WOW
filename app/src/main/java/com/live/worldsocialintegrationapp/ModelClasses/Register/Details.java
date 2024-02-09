package com.live.worldsocialintegrationapp.ModelClasses.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Details implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("host_status")
    @Expose
    private String hostStatus;
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
    private String vipFrom;
    @SerializedName("vipTo")
    @Expose
    private String vipTo;
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
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("country_showUnshow")
    @Expose
    private String countryShowUnshow;
    @SerializedName("myFrame")
    @Expose
    private String myFrame;
    @SerializedName("myLuckyId")
    @Expose
    private String myLuckyId;
    @SerializedName("email_verified_at")
    @Expose
    private String emailVerifiedAt;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("salt")
    @Expose
    private String salt;

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
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
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
    @SerializedName("monthlyCoins")
    @Expose
    private String monthlyCoins;
    @SerializedName("hoursLive")
    @Expose
    private String hoursLive;
    @SerializedName("talent_level")
    @Expose
    private String talentLevel;
    @SerializedName("basicSalary")
    @Expose
    private String basicSalary;
    @SerializedName("userBanStatus")
    @Expose
    private String userBanStatus;
    @SerializedName("idBannedFrom")
    @Expose
    private String idBannedFrom;
    @SerializedName("idBannedTo")
    @Expose
    private String idBannedTo;
    @SerializedName("liveStatus")
    @Expose
    private String liveStatus;
    @SerializedName("Liveimage")
    @Expose
    private String liveimage;
    @SerializedName("imageText")
    @Expose
    private String imageText;
    @SerializedName("imageTitle")
    @Expose
    private String imageTitle;
    @SerializedName("fixed_valid_day")
    @Expose
    private String fixedValidDay;
    @SerializedName("fixed_minutes")
    @Expose
    private String fixedMinutes;
    @SerializedName("fixed_coins")
    @Expose
    private String fixedCoins;
    @SerializedName("imageDp")
    @Expose
    private String imageDp;
    @SerializedName("idBannedStatus")
    @Expose
    private Boolean idBannedStatus;

    @SerializedName("forgotPassword")
    @Expose
    private String forgotPassword;
    @SerializedName("receivingLevel")
    @Expose
    private String receivingLevel;
    @SerializedName("myGallery")
    @Expose
    private String myGallery;
    @SerializedName("myTheme")
    @Expose
    private String myTheme;

    public String getReceivingLevel() {
        return receivingLevel;
    }

    public void setReceivingLevel(String receivingLevel) {
        this.receivingLevel = receivingLevel;
    }

    public String getMyGallery() {
        return myGallery;
    }

    public void setMyGallery(String myGallery) {
        this.myGallery = myGallery;
    }

    public String getMyTheme() {
        return myTheme;
    }

    public void setMyTheme(String myTheme) {
        this.myTheme = myTheme;
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

    public String getHostStatus() {
        return hostStatus;
    }

    public void setHostStatus(String hostStatus) {
        this.hostStatus = hostStatus;
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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
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

    public String getSalt(){
        return salt;
    }
    public void setSalt(String salt){this.salt = salt;}

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

    public String getHoursLive() {
        return hoursLive;
    }

    public void setHoursLive(String hoursLive) {
        this.hoursLive = hoursLive;
    }

    public String getTalentLevel() {
        return talentLevel;
    }

    public void setTalentLevel(String talentLevel) {
        this.talentLevel = talentLevel;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getUserBanStatus() {
        return userBanStatus;
    }

    public void setUserBanStatus(String userBanStatus) {
        this.userBanStatus = userBanStatus;
    }

    public String getIdBannedFrom() {
        return idBannedFrom;
    }

    public void setIdBannedFrom(String idBannedFrom) {
        this.idBannedFrom = idBannedFrom;
    }

    public String getIdBannedTo() {
        return idBannedTo;
    }

    public void setIdBannedTo(String idBannedTo) {
        this.idBannedTo = idBannedTo;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
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

    public String getFixedValidDay() {
        return fixedValidDay;
    }

    public void setFixedValidDay(String fixedValidDay) {
        this.fixedValidDay = fixedValidDay;
    }

    public String getFixedMinutes() {
        return fixedMinutes;
    }

    public void setFixedMinutes(String fixedMinutes) {
        this.fixedMinutes = fixedMinutes;
    }

    public String getFixedCoins() {
        return fixedCoins;
    }

    public void setFixedCoins(String fixedCoins) {
        this.fixedCoins = fixedCoins;
    }

    public String getImageDp() {
        return imageDp;
    }

    public void setImageDp(String imageDp) {
        this.imageDp = imageDp;
    }

    public Boolean getIdBannedStatus() {
        return idBannedStatus;
    }

    public void setIdBannedStatus(Boolean idBannedStatus) {
        this.idBannedStatus = idBannedStatus;
    }

    public String getForgotPassword(){return  forgotPassword;};

    public void setForgotPassword(String forgotPassword){this.forgotPassword = forgotPassword;}
}
