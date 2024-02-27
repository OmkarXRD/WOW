package com.live.worldsocialintegrationapp.ModelClasses.UploadPost;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class RemoveUserPostRoot implements Serializable {
    public String success;
    public String message;

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
}
