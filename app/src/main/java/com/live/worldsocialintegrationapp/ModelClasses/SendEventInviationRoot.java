package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;

public class SendEventInviationRoot implements Serializable {

    public int success;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message;

}
