package com.live.worldsocialintegrationapp.ModelClasses.GetFriends;

import java.io.Serializable;

public class Detail implements Serializable {

    public String followingUserId;
    public String id;
    public String name;
    public String purchasedCoin;
    public String coin;
    public String diamond;
    public String dob;
    public String email;
//    @JsonProperty("Country")
    public String country;
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
    public String created_at;
    public String updated_at;
    public String imageDp;
    public LavelInformation lavelInformation;

    public LavelInformation getLavelInformation() {
        return lavelInformation;
    }

    public void setLavelInformation(LavelInformation lavelInformation) {
        this.lavelInformation = lavelInformation;
    }

    public String getImageDp() {
        return imageDp;
    }

    public void setImageDp(String imageDp) {
        this.imageDp = imageDp;
    }

    public String getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(String followingUserId) {
        this.followingUserId = followingUserId;
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

    public String getPurchasedCoin() {
        return purchasedCoin;
    }

    public void setPurchasedCoin(String purchasedCoin) {
        this.purchasedCoin = purchasedCoin;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
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

    public class LavelInformation{
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
}
