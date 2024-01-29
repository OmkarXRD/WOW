package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class GetFramesRoot implements Serializable {

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

    public class  Detail {
        public String id;
        public String frame_img;
        public String price;
        public String validity;
        public String type;
        public String created;
        public String updated;
        public boolean isMy;
        public String otherUserId;
        public String frameId;
        public String rewardType;
        public String dateFrom;
        public String dateTo;

        public String getOtherUserId() {
            return otherUserId;
        }

        public void setOtherUserId(String otherUserId) {
            this.otherUserId = otherUserId;
        }

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public String getRewardType() {
            return rewardType;
        }

        public void setRewardType(String rewardType) {
            this.rewardType = rewardType;
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

        public String getRemainingDays() {
            return remainingDays;
        }

        public void setRemainingDays(String remainingDays) {
            this.remainingDays = remainingDays;
        }

        public String remainingDays;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFrame_img() {
            return frame_img;
        }

        public void setFrame_img(String frame_img) {
            this.frame_img = frame_img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public boolean isMy() {
            return isMy;
        }

        public void setMy(boolean my) {
            isMy = my;
        }
    }
}
