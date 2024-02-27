package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class GetAppliedFrameRoot implements Serializable {


    public int status;
    public String message;
    public Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details{
        public String id;
        public String frame_img;
        public String price;
        public String validity;
        public String type;
        public String created;
        public String updated;

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
    }
}
