package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class SocialLoginRoot implements Serializable {

    public int status;
    public String message;
    public Details details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        public String id;
        public String name;
        public String facebookUserName;
        public String host_status;
        public String social_id;
        public String myExp;
        public String myRecieveExperience;
        public String vipLevel;
        public String vipFrom;
        public String vipTo;
        public String myLevel;
        public String receivingLevel;
        public String myCoin;
        public String myDiamond;
        public String totalSendDiamond;
        public String myRecievedDiamond;
        public String dob;
        public String bio;
        public String email;
        public String country;
        public String continent;
        public String country_showUnshow;
        public String myFrame;
        public String myGallery;
        public String myLuckyId;
        public String myTheme;
        public String email_verified_at;
        public String password;
        public String is_admin;
        public String phone;
        public String image;
        public String username;
        public String lang_id;
        public String age;
        public String gender;
        public String dev_id;
        public String reg_id;
        public String latitude;
        public String longitude;
        public String dev_type;
        public String remember_token;
        public String loginOtp;
        public String login_type;
        public String registerType;
        public String created_at;
        public String updated_at;
        public String phoneUpOtp;
        public String familyId;
        public String eventId;
        public String isEventCreater;
        public String isEventSubscriber;
        public String isFamilyLeader;
        public String isFamilyMember;
        public String monthlyCoins;
        public String hoursLive;
        public String talent_level;
        public String basicSalary;
        public String userBanStatus;
        public String idBannedFrom;
        public String idBannedTo;
        public String liveStatus;
        public String liveimage;
        public String imageText;
        public String imageTitle;
        public String fixed_valid_day;
        public String fixed_minutes;
        public String fixed_coins;
        public boolean idBannedStatus;

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

        public String getFacebookUserName() {
            return facebookUserName;
        }

        public void setFacebookUserName(String facebookUserName) {
            this.facebookUserName = facebookUserName;
        }


        public String getHost_status() {
            return host_status;
        }

        public void setHost_status(String host_status) {
            this.host_status = host_status;
        }

        public String getSocial_id() {
            return social_id;
        }

        public void setSocial_id(String social_id) {
            this.social_id = social_id;
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

        public String getReceivingLevel() {
            return receivingLevel;
        }

        public void setReceivingLevel(String receivingLevel) {
            this.receivingLevel = receivingLevel;
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

        public String getCountry_showUnshow() {
            return country_showUnshow;
        }

        public void setCountry_showUnshow(String country_showUnshow) {
            this.country_showUnshow = country_showUnshow;
        }

        public String getMyFrame() {
            return myFrame;
        }

        public void setMyFrame(String myFrame) {
            this.myFrame = myFrame;
        }

        public String getMyGallery() {
            return myGallery;
        }

        public void setMyGallery(String myGallery) {
            this.myGallery = myGallery;
        }

        public String getMyLuckyId() {
            return myLuckyId;
        }

        public void setMyLuckyId(String myLuckyId) {
            this.myLuckyId = myLuckyId;
        }

        public String getMyTheme() {
            return myTheme;
        }

        public void setMyTheme(String myTheme) {
            this.myTheme = myTheme;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
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

        public String getLang_id() {
            return lang_id;
        }

        public void setLang_id(String lang_id) {
            this.lang_id = lang_id;
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

        public String getDev_id() {
            return dev_id;
        }

        public void setDev_id(String dev_id) {
            this.dev_id = dev_id;
        }

        public String getReg_id() {
            return reg_id;
        }

        public void setReg_id(String reg_id) {
            this.reg_id = reg_id;
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

        public String getDev_type() {
            return dev_type;
        }

        public void setDev_type(String dev_type) {
            this.dev_type = dev_type;
        }

        public String getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(String remember_token) {
            this.remember_token = remember_token;
        }

        public String getLoginOtp() {
            return loginOtp;
        }

        public void setLoginOtp(String loginOtp) {
            this.loginOtp = loginOtp;
        }

        public String getLogin_type() {
            return login_type;
        }

        public void setLogin_type(String login_type) {
            this.login_type = login_type;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
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

        public String getTalent_level() {
            return talent_level;
        }

        public void setTalent_level(String talent_level) {
            this.talent_level = talent_level;
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

        public String getFixed_valid_day() {
            return fixed_valid_day;
        }

        public void setFixed_valid_day(String fixed_valid_day) {
            this.fixed_valid_day = fixed_valid_day;
        }

        public String getFixed_minutes() {
            return fixed_minutes;
        }

        public void setFixed_minutes(String fixed_minutes) {
            this.fixed_minutes = fixed_minutes;
        }

        public String getFixed_coins() {
            return fixed_coins;
        }

        public void setFixed_coins(String fixed_coins) {
            this.fixed_coins = fixed_coins;
        }

        public boolean isIdBannedStatus() {
            return idBannedStatus;
        }

        public void setIdBannedStatus(boolean idBannedStatus) {
            this.idBannedStatus = idBannedStatus;
        }
    }
}
