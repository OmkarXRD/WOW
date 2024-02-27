package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
@Keep
public class Details {
    @SerializedName("coins")
    @Expose
    private List<Coin> coins = null;
    @SerializedName("Quantity")
    @Expose
    private List<Quantity> quantity = null;

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Quantity> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Quantity> quantity) {
        this.quantity = quantity;
    }
}
