package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
public class Quantity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("coin_quantity")
    @Expose
    private String coinQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoinQuantity() {
        return coinQuantity;
    }

    public void setCoinQuantity(String coinQuantity) {
        this.coinQuantity = coinQuantity;
    }

}
