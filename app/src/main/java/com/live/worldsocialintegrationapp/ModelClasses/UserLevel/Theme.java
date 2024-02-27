package com.live.worldsocialintegrationapp.ModelClasses.UserLevel;

import androidx.annotation.Keep;

@Keep
public class Theme {
    public String userId;
    public String themeId;
    public String theme;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
