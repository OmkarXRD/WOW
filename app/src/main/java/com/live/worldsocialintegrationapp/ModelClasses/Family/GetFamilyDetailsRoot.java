package com.live.worldsocialintegrationapp.ModelClasses.Family;

import java.io.Serializable;
import java.util.ArrayList;

public class GetFamilyDetailsRoot implements Serializable {

    public String success;
    public String message;
    public Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {
        public String id;
        public String familyName;
        public String description;
        public String leaderId;
        public int members;
        public String image;
        public boolean admin;
        public String created_at;
        public boolean family_create_status;
        public ArrayList<Joiner> joiner;
        public String family_OwnerName;
        public String leaderImage;
        public String uniqueId;
        public String status;
        public String edit_status;
        public int request_count;
        public int familyLevel;
        public int totalRecieving;
        public int totalExp;


        public int getTotalRecieving() {
            return totalRecieving;
        }
        public int getTotalExp() {
            return totalExp;
        }

        public void setTotalRecieving(int totalRecieving) {
            this.totalRecieving = totalRecieving;
        }

        public int getRequest_count() {
            return request_count;
        }
        public int getFamilyLevel() {
            return familyLevel;
        }

        public void setRequest_count(int request_count) {
            this.request_count = request_count;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEdit_status() {
            return edit_status;
        }

        public void setEdit_status(String edit_status) {
            this.edit_status = edit_status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLeaderId() {
            return leaderId;
        }

        public void setLeaderId(String leaderId) {
            this.leaderId = leaderId;
        }

        public int getMembers() {
            return members;
        }

        public void setMembers(int members) {
            this.members = members;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public boolean isFamily_create_status() {
            return family_create_status;
        }

        public void setFamily_create_status(boolean family_create_status) {
            this.family_create_status = family_create_status;
        }

        public ArrayList<Joiner> getJoiner() {
            return joiner;
        }

        public void setJoiner(ArrayList<Joiner> joiner) {
            this.joiner = joiner;
        }

        public String getFamily_OwnerName() {
            return family_OwnerName;
        }

        public void setFamily_OwnerName(String family_OwnerName) {
            this.family_OwnerName = family_OwnerName;
        }

        public String getLeaderImage() {
            return leaderImage;
        }

        public void setLeaderImage(String leaderImage) {
            this.leaderImage = leaderImage;
        }
    }
}
