package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;

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