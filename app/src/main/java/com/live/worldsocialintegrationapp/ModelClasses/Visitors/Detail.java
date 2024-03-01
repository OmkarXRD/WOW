package com.live.worldsocialintegrationapp.ModelClasses.Visitors;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.ArrayList;
@Keep
public class Detail  implements Serializable {

    public String id;
    public String userId;
    public String otherUserId;
    public String visited;
    public String created;
    public ArrayList<UserDeatil> userDeatils;

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

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getVisited() {
        return visited;
    }

    public void setVisited(String visited) {
        this.visited = visited;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ArrayList<UserDeatil> getUserDeatils() {
        return userDeatils;
    }

    public void setUserDeatils(ArrayList<UserDeatil> userDeatils) {
        this.userDeatils = userDeatils;
    }
}
