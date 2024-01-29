package com.live.worldsocialintegrationapp.ModelClasses.GetCoin;

import java.io.Serializable;

public class Detail implements Serializable {

    public String id;
    public String coinValue;
    public String moneyValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(String coinValue) {
        this.coinValue = coinValue;
    }

    public String getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(String moneyValue) {
        this.moneyValue = moneyValue;
    }
}
