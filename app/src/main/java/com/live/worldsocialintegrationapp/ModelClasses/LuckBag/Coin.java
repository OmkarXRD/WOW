package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("coins")
    @Expose
    private String coins;


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
}
