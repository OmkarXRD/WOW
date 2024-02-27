package com.live.worldsocialintegrationapp.agora.openvcall.model;

import androidx.annotation.Keep;

@Keep
public class CurrentUserSettings {
    public int mEncryptionModeIndex;
    public String mEncryptionKey;
    public String mChannelName;
    public CurrentUserSettings() {
        reset();
    }
    public void reset() {
    }
}
