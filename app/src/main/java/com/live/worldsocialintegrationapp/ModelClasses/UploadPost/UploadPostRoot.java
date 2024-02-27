package com.live.worldsocialintegrationapp.ModelClasses.UploadPost;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class UploadPostRoot implements Serializable {

    public String message;
    public String success;
    public Details details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
