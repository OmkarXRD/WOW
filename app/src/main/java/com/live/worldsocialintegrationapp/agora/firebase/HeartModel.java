package com.live.worldsocialintegrationapp.agora.firebase;

import androidx.annotation.Keep;

@Keep
public class HeartModel {

    String position = "";
    String random = "";

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
