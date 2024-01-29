package com.live.worldsocialintegrationapp.ModelClasses.UserLevel;

public class LuckyId {

    public String userId;
    public String luckyId;
//    @JsonProperty("LuckyIdImage")
    public String LuckyIdImage;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLuckyId() {
        return luckyId;
    }

    public void setLuckyId(String luckyId) {
        this.luckyId = luckyId;
    }

    public String getLuckyIdImage() {
        return LuckyIdImage;
    }

    public void setLuckyIdImage(String luckyIdImage) {
        LuckyIdImage = luckyIdImage;
    }
}
