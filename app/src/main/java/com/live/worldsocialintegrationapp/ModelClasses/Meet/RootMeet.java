package com.live.worldsocialintegrationapp.ModelClasses.Meet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RootMeet implements Serializable {

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


        @SerializedName("diamond")
        @Expose
        private String diamond;
        @SerializedName("receiverId")
        @Expose
        private String receiverId;
        @SerializedName("id")
        @Expose
        private String id;
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
        @SerializedName("myFrame")
        @Expose
        private Object myFrame;
        @SerializedName("myLuckyId")
        @Expose
        private Object myLuckyId;
        @SerializedName("email_verified_at")
        @Expose
        private String emailVerifiedAt;
        @SerializedName("password")
        @Expose
        private String password;
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
        @SerializedName("isFamilyLeader")
        @Expose
        private String isFamilyLeader;
        @SerializedName("isFamilyMember")
        @Expose
        private String isFamilyMember;
        @SerializedName("imageDp")
        @Expose
        private String imageDp;

        public String getDiamond() {
            return diamond;
        }

        public void setDiamond(String diamond) {
            this.diamond = diamond;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
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