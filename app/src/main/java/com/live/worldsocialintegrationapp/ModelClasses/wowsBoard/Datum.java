package com.live.worldsocialintegrationapp.ModelClasses.wowsBoard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("diamond")
    @Expose
    private String diamond;
    @SerializedName("senderId")
    @Expose
    private String senderId;
    @SerializedName("userDetails")
    @Expose
    private UserDetails userDetails;

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
