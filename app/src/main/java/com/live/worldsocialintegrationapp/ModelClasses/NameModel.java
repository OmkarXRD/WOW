package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class NameModel implements Serializable {

    private String name;

    public NameModel() {
    }

    public NameModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}