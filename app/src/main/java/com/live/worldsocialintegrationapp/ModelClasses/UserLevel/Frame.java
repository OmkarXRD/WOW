package com.live.worldsocialintegrationapp.ModelClasses.UserLevel;

import androidx.annotation.Keep;

@Keep
public class Frame {
    public String userId;
    public String frameId;
//    @JsonProperty("FrameImage")
    public String FrameImage;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getFrameImage() {
        return FrameImage;
    }

    public void setFrameImage(String frameImage) {
        FrameImage = frameImage;
    }
}
