package com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory;

import androidx.annotation.Keep;

@Keep
public class PurchaseLuckyIdHistory {

    public String id;
    public String userId;
    public String luckyId;
    public String amount;
    public String created;
    public String updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
