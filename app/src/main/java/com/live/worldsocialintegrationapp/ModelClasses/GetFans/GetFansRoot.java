package com.live.worldsocialintegrationapp.ModelClasses.GetFans;

import java.io.Serializable;
import java.util.ArrayList;

public class GetFansRoot implements Serializable {

    public String status;
    public String message;
    public ArrayList<Detail> details;


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

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }
}
