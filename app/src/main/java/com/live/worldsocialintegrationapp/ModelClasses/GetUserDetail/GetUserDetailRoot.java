package com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail;

import java.io.Serializable;

public class GetUserDetailRoot implements Serializable {

    public String status;
    public String message;
    public Details details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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



