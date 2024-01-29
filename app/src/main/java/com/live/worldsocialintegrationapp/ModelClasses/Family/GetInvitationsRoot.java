package com.live.worldsocialintegrationapp.ModelClasses.Family;

import java.io.Serializable;
import java.util.ArrayList;

public class GetInvitationsRoot implements Serializable {

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

    public class Detail{
//        public String id;
//        public String familyId;
//        public String userId;
//        public String type;
//        public String status;
//        public String date;
//        public String name;
//        public String dob;
//        public String username;
//        public String myCoin;
//        public String myDiamond;
//        public String imageDp;

        public String id;
        public String familyId;
        public String userId;
        public String type;
        public String status;
        public String date;
        public String image;
        public String description;
        public String familyName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFamilyId() {
            return familyId;
        }

        public void setFamilyId(String familyId) {
            this.familyId = familyId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }
    }
}
