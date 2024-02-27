package com.live.worldsocialintegrationapp.agora.firebase;

import androidx.annotation.Keep;

@Keep
public class PKLiveUserModel {



    @Keep
    public class Dimaond{

        private String name="";
        private String image ="";
        private String userId="";

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }


}
