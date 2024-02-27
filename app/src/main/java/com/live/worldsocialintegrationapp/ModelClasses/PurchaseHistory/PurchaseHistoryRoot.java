package com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class PurchaseHistoryRoot implements Serializable {

    public String success;
    public String message;
    public Details details;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
