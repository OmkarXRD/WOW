package com.live.worldsocialintegrationapp.ModelClasses;

public class PurchaseGallery {

    String coins;

    public PurchaseGallery(String coins, String validity) {
        this.coins = coins;
        this.validity = validity;
    }

    String validity;

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }



}
