package com.live.worldsocialintegrationapp.ModelClasses.UserLevel;

import java.util.ArrayList;

public class GetCarUserLevel {

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
        public String image;
        public String level;
        public String price;
        public String validity;
        public boolean available;

        public ArrayList<LuckyId> luckyId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public ArrayList<LuckyId> getLuckyId() {
            return luckyId;
        }

        public void setLuckyId(ArrayList<LuckyId> luckyId) {
            this.luckyId = luckyId;
        }
    }
}



