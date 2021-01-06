package com.tapumandal.ecommerce.domain.business_settings;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TapuMandal on 11/25/2020.
 * For any query ask online.tapu@gmail.com
 */

public class VersionControlModelDto implements Serializable {

    @SerializedName("isForce")
    protected boolean force = false;

    @SerializedName("version")
    protected int appVersion = 1;

    @SerializedName("forceable_version")
    protected int forceableVersion = 1;

    @SerializedName("message")
    protected String message = "";

    @SerializedName("title")
    protected String title = "";

    @SerializedName("display_version")
    protected String displayVersion = "";

    @SerializedName("change_log")
    protected String changeLog = "";


    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public int getForceableVersion() {
        return forceableVersion;
    }

    public void setForceableVersion(int forceableVersion) {
        this.forceableVersion = forceableVersion;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayVersion() {
        return displayVersion == null ? "" : displayVersion;
    }

    public void setDisplayVersion(String displayVersion) {
        this.displayVersion = displayVersion;
    }

    public String getChangeLog() {
        return changeLog == null ? "" : changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }
}
