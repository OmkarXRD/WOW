package com.live.worldsocialintegrationapp.ModelClasses.GetCoin;

import java.io.Serializable;
import java.util.ArrayList;

public class GetCoinRoot implements Serializable {


    public String status;
    public String message;
    public String phone;
    public ArrayList<Detail> details;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
