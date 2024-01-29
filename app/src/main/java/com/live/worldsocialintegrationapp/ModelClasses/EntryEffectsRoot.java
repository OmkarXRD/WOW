package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class EntryEffectsRoot implements Serializable {

    public String success;
    public String message;
    public ArrayList<Detail> details;

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

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    public class  Detail{

        public String id;
        public String gifUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGifUrl() {
            return gifUrl;
        }

        public void setGifUrl(String gifUrl) {
            this.gifUrl = gifUrl;
        }
    }
}
