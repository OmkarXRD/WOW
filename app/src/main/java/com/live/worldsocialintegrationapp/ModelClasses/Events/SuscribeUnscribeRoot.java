package com.live.worldsocialintegrationapp.ModelClasses.Events;

import java.io.Serializable;

public class SuscribeUnscribeRoot implements Serializable {
    public String success;
    public String message;
    public boolean subscribe_status;

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

    public boolean isSubscribe_status() {
        return subscribe_status;
    }

    public void setSubscribe_status(boolean subscribe_status) {
        this.subscribe_status = subscribe_status;
    }
}
