package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class GetUserImagesRoot {

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

    public class  Detail{
        public String image;
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
