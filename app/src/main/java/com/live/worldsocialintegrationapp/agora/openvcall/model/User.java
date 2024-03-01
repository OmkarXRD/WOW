package com.live.worldsocialintegrationapp.agora.openvcall.model;

import androidx.annotation.Keep;

@Keep
public class User {
    public User(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public final int uid;
    public final String name;
}
