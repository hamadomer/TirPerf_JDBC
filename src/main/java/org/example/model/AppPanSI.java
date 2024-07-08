package org.example.model;

public class AppPanSI {
    private int pansiId;
    private int appId;
    private String appVersion;

    public AppPanSI() {}

    public AppPanSI(int pansiId, int appId, String appVersion) {
        this.pansiId = pansiId;
        this.appId = appId;
        this.appVersion = appVersion;
    }

    public int getPansiId() {
        return pansiId;
    }

    public void setPansiId(int pansiId) {
        this.pansiId = pansiId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}