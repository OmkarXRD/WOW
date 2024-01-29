package com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Details implements Serializable {

    public String id;
    public String name;
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
//    @JsonProperty("Country")
    public String Country;
    public String country_showUnshow;
    public String myFrame;
    public String myLuckyId;
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
    public String followersCount;
    public String followingCount;
    public boolean agencyCreater;
    public boolean coinAgencyCreater;
    public boolean idBannedStatus;
    public boolean  vipStatus;
    public String hostRequest;
    public String archivedTime;
    public boolean agency_status;

    public boolean isAgency_status() {
        return agency_status;
    }

    public void setAgency_status(boolean agency_status) {
        this.agency_status = agency_status;
    }

    public String getArchivedTime() {
        return archivedTime;
    }

    public void setArchivedTime(String archivedTime) {
        this.archivedTime = archivedTime;
    }

    public String getHostRequest() {
        return hostRequest;
    }

    public void setHostRequest(String hostRequest) {
        this.hostRequest = hostRequest;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public LavelInfomation lavelInfomation;

    public LavelInfomation getLavelInfomation() {
        return lavelInfomation;
    }

    public void setLavelInfomation(LavelInfomation lavelInfomation) {
        this.lavelInfomation = lavelInfomation;
    }

    public String getReceivingLevel() {
        return receivingLevel;
    }

    public void setReceivingLevel(String receivingLevel) {
        this.receivingLevel = receivingLevel;
    }

    public boolean isIdBannedStatus() {
        return idBannedStatus;
    }

    public void setIdBannedStatus(boolean idBannedStatus) {
        this.idBannedStatus = idBannedStatus;
    }

    public boolean isCoinAgencyCreater() {
        return coinAgencyCreater;
    }

    public void setCoinAgencyCreater(boolean coinAgencyCreater) {
        this.coinAgencyCreater = coinAgencyCreater;
    }

    public boolean isAgencyCreater() {
        return agencyCreater;
    }

    public void setAgencyCreater(boolean agencyCreater) {
        this.agencyCreater = agencyCreater;
    }

    public String visitorsCount;
    public String friendsCount;
    public boolean followStatus;
    public boolean liveStatus;
    public UserLive userLive;
    public String profileImage;
    public boolean hideStatus;
    public boolean kickOutStatus;
    public boolean vip_status;
    public boolean blockStatus;
    public String familyName;
    public String familyImage;
    public String host_status;
    public String country;
    public String hoursLive;
    public String talent_level;
    public String basicSalary;
    public String userBanStatus;

    public String continent;

    public String liveimage;
    public String imageText;
    public String imageTitle;
    public boolean familyJoinStatus;
    public VipDetails vip_details;
    public String myGallery;
    public String idBannedFrom;
    public String idBannedTo;
    public String fixed_valid_day;
    public String fixed_minutes;
    public String fixed_coins;
    public String familyJoinName;
    public String familyJoinId;
    public int friendCount;

    public String getFamilyJoinId() {
        return familyJoinId;
    }

    public void setFamilyJoinId(String familyJoinId) {
        this.familyJoinId = familyJoinId;
    }

    public String getMyGallery() {
        return myGallery;
    }

    public void setMyGallery(String myGallery) {
        this.myGallery = myGallery;
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

    public String getFamilyJoinName() {
        return familyJoinName;
    }

    public void setFamilyJoinName(String familyJoinName) {
        this.familyJoinName = familyJoinName;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public boolean isFamilyJoinStatus() {
        return familyJoinStatus;
    }

    public void setFamilyJoinStatus(boolean familyJoinStatus) {
        this.familyJoinStatus = familyJoinStatus;
    }

    public VipDetails getVip_details() {
        return vip_details;
    }

    public void setVip_details(VipDetails vip_details) {
        this.vip_details = vip_details;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
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

//    public VipDetails getVip_details() {
//        return vip_details;
//    }
//
//    public void setVip_details(VipDetails vip_details) {
//        this.vip_details = vip_details;
//    }

    public String getHost_status() {

        return host_status;
    }

    public void setHost_status(String host_status) {

        this.host_status = host_status;
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyImage() {
        return familyImage;
    }

    public void setFamilyImage(String familyImage) {
        this.familyImage = familyImage;
    }

    public boolean isBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(boolean blockStatus) {
        this.blockStatus = blockStatus;
    }

    public boolean isVip_status() {
        return vip_status;
    }

    public void setVip_status(boolean vip_status) {
        this.vip_status = vip_status;
    }

    public boolean isHideStatus() {
        return hideStatus;
    }

    public void setHideStatus(boolean hideStatus) {
        this.hideStatus = hideStatus;
    }

    public boolean isKickOutStatus() {
        return kickOutStatus;
    }

    public void setKickOutStatus(boolean kickOutStatus) {
        this.kickOutStatus = kickOutStatus;
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
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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

    public String getMyLuckyId() {
        return myLuckyId;
    }

    public void setMyLuckyId(String myLuckyId) {
        this.myLuckyId = myLuckyId;
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

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }

    public String getVisitorsCount() {
        return visitorsCount;
    }

    public void setVisitorsCount(String visitorsCount) {
        this.visitorsCount = visitorsCount;
    }

    public String getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(String friendsCount) {
        this.friendsCount = friendsCount;
    }

    public boolean isFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(boolean followStatus) {
        this.followStatus = followStatus;
    }

    public boolean isLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(boolean liveStatus) {
        this.liveStatus = liveStatus;
    }

    public UserLive getUserLive() {
        return userLive;
    }

    public void setUserLive(UserLive userLive) {
        this.userLive = userLive;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    public class LavelInfomation{
        public String sandColor;
        public String sendLevel;
        public String sendExp;
        public int sendStart;
        public int sendEnd;
        public String reciveColor;
        public String reciveLevel;
        public String reciveExp;
        public int reciveStart;
        public int reciveEnd;

        public String getSandColor() {
            return sandColor;
        }

        public void setSandColor(String sandColor) {
            this.sandColor = sandColor;
        }

        public String getSendLevel() {
            return sendLevel;
        }

        public void setSendLevel(String sendLevel) {
            this.sendLevel = sendLevel;
        }

        public String getSendExp() {
            return sendExp;
        }

        public void setSendExp(String sendExp) {
            this.sendExp = sendExp;
        }

        public int getSendStart() {
            return sendStart;
        }

        public void setSendStart(int sendStart) {
            this.sendStart = sendStart;
        }

        public int getSendEnd() {
            return sendEnd;
        }

        public void setSendEnd(int sendEnd) {
            this.sendEnd = sendEnd;
        }

        public String getReciveColor() {
            return reciveColor;
        }

        public void setReciveColor(String reciveColor) {
            this.reciveColor = reciveColor;
        }

        public String getReciveLevel() {
            return reciveLevel;
        }

        public void setReciveLevel(String reciveLevel) {
            this.reciveLevel = reciveLevel;
        }

        public String getReciveExp() {
            return reciveExp;
        }

        public void setReciveExp(String reciveExp) {
            this.reciveExp = reciveExp;
        }

        public int getReciveStart() {
            return reciveStart;
        }

        public void setReciveStart(int reciveStart) {
            this.reciveStart = reciveStart;
        }

        public int getReciveEnd() {
            return reciveEnd;
        }

        public void setReciveEnd(int reciveEnd) {
            this.reciveEnd = reciveEnd;
        }
    }

    public class UserLive{

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
        public Boolean kickOutStatus;

        public Boolean getKickOutStatus() {
            return kickOutStatus;
        }

        public void setKickOutStatus(Boolean kickOutStatus) {
            this.kickOutStatus = kickOutStatus;
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
    }

    public class VipDetails{
        public String id;
        public String coins;
        public String batch;
        public String vipicon;
        public String uniqueframes;
        public String entranceeffect;
        public String getthiscar;
        public String friends;
        public String following;
        public String coinsPerDay;
        public String colorfullMessage;
        public String flyingComment;
        public String hdeCountryAndOnlineTime;
        public String exclusiveGifts;
        public String preventFromBeingKicked;
        public String antiBan;
        public String valid;
        public String vipIconImage;
        public String uniqueFrameImage;
        public String entranceEffectImage;
        public String getThisCarImage;
        public String friendsImage;
        public String followingFriends;
        public String coinsImage;
        public String mainImage;
        public String colorMessageImage;
        public String flyingCommentImage;
        public String exclusiveGiftImage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getVipicon() {
            return vipicon;
        }

        public void setVipicon(String vipicon) {
            this.vipicon = vipicon;
        }

        public String getUniqueframes() {
            return uniqueframes;
        }

        public void setUniqueframes(String uniqueframes) {
            this.uniqueframes = uniqueframes;
        }

        public String getEntranceeffect() {
            return entranceeffect;
        }

        public void setEntranceeffect(String entranceeffect) {
            this.entranceeffect = entranceeffect;
        }

        public String getGetthiscar() {
            return getthiscar;
        }

        public void setGetthiscar(String getthiscar) {
            this.getthiscar = getthiscar;
        }

        public String getFriends() {
            return friends;
        }

        public void setFriends(String friends) {
            this.friends = friends;
        }

        public String getFollowing() {
            return following;
        }

        public void setFollowing(String following) {
            this.following = following;
        }

        public String getCoinsPerDay() {
            return coinsPerDay;
        }

        public void setCoinsPerDay(String coinsPerDay) {
            this.coinsPerDay = coinsPerDay;
        }

        public String getColorfullMessage() {
            return colorfullMessage;
        }

        public void setColorfullMessage(String colorfullMessage) {
            this.colorfullMessage = colorfullMessage;
        }

        public String getFlyingComment() {
            return flyingComment;
        }

        public void setFlyingComment(String flyingComment) {
            this.flyingComment = flyingComment;
        }

        public String getHdeCountryAndOnlineTime() {
            return hdeCountryAndOnlineTime;
        }

        public void setHdeCountryAndOnlineTime(String hdeCountryAndOnlineTime) {
            this.hdeCountryAndOnlineTime = hdeCountryAndOnlineTime;
        }

        public String getExclusiveGifts() {
            return exclusiveGifts;
        }

        public void setExclusiveGifts(String exclusiveGifts) {
            this.exclusiveGifts = exclusiveGifts;
        }

        public String getPreventFromBeingKicked() {
            return preventFromBeingKicked;
        }

        public void setPreventFromBeingKicked(String preventFromBeingKicked) {
            this.preventFromBeingKicked = preventFromBeingKicked;
        }

        public String getAntiBan() {
            return antiBan;
        }

        public void setAntiBan(String antiBan) {
            this.antiBan = antiBan;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getVipIconImage() {
            return vipIconImage;
        }

        public void setVipIconImage(String vipIconImage) {
            this.vipIconImage = vipIconImage;
        }

        public String getUniqueFrameImage() {
            return uniqueFrameImage;
        }

        public void setUniqueFrameImage(String uniqueFrameImage) {
            this.uniqueFrameImage = uniqueFrameImage;
        }

        public String getEntranceEffectImage() {
            return entranceEffectImage;
        }

        public void setEntranceEffectImage(String entranceEffectImage) {
            this.entranceEffectImage = entranceEffectImage;
        }

        public String getGetThisCarImage() {
            return getThisCarImage;
        }

        public void setGetThisCarImage(String getThisCarImage) {
            this.getThisCarImage = getThisCarImage;
        }

        public String getFriendsImage() {
            return friendsImage;
        }

        public void setFriendsImage(String friendsImage) {
            this.friendsImage = friendsImage;
        }

        public String getFollowingFriends() {
            return followingFriends;
        }

        public void setFollowingFriends(String followingFriends) {
            this.followingFriends = followingFriends;
        }

        public String getCoinsImage() {
            return coinsImage;
        }

        public void setCoinsImage(String coinsImage) {
            this.coinsImage = coinsImage;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        public String getColorMessageImage() {
            return colorMessageImage;
        }

        public void setColorMessageImage(String colorMessageImage) {
            this.colorMessageImage = colorMessageImage;
        }

        public String getFlyingCommentImage() {
            return flyingCommentImage;
        }

        public void setFlyingCommentImage(String flyingCommentImage) {
            this.flyingCommentImage = flyingCommentImage;
        }

        public String getExclusiveGiftImage() {
            return exclusiveGiftImage;
        }

        public void setExclusiveGiftImage(String exclusiveGiftImage) {
            this.exclusiveGiftImage = exclusiveGiftImage;
        }
    }
}