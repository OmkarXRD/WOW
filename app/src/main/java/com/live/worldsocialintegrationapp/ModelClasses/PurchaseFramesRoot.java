package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchaseFramesRoot implements Serializable {


    //purchase frame and getPurchase frame api
    public int status;
    public String message;
    public ArrayList<Detail> details;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public class Detail {

        public String id;
        public String userId;
        public String frameId;
        public String price;
        public String dateFrom;
        public String dateTo;
        public String frameIMage;
        public boolean isApplied;
        public String exp_time;

        public String getExp_time() {
            return exp_time;
        }

        public void setExp_time(String exp_time) {
            this.exp_time = exp_time;
        }

        public boolean isApplied() {
            return isApplied;
        }

        public void setApplied(boolean applied) {
            isApplied = applied;
        }

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

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
        }

        public String getDateTo() {
            return dateTo;
        }

        public void setDateTo(String dateTo) {
            this.dateTo = dateTo;
        }

        public String getFrameIMage() {
            return frameIMage;
        }

        public void setFrameIMage(String frameIMage) {
            this.frameIMage = frameIMage;
        }
    }

}
