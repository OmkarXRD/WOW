package com.live.worldsocialintegrationapp.agora.openvcall.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenGenerateModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("details")
    @Expose
    private Details details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("dob")
        @Expose
        public String dob;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("userId")
        @Expose
        public String userId;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("toke")
        @Expose
        public String toke;
        @SerializedName("channelName")
        @Expose
        public String channelName;
        @SerializedName("rtmToken")
        @Expose
        public String rtmToken;
        @SerializedName("mainId")
        @Expose
        public String mainId;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getToke() {
            return toke;
        }

        public void setToke(String toke) {
            this.toke = toke;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getRtmToken() {
            return rtmToken;
        }

        public void setRtmToken(String rtmToken) {
            this.rtmToken = rtmToken;
        }

        public String getMainId() {
            return mainId;
        }

        public void setMainId(String mainId) {
            this.mainId = mainId;
        }
    }
}