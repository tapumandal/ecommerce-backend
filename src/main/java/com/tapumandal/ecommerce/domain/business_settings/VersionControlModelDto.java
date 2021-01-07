package com.tapumandal.ecommerce.domain.business_settings;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by TapuMandal on 11/25/2020.
 * For any query ask online.tapu@gmail.com
 */

@Component
public class VersionControlModelDto implements Serializable{

    @SerializedName("isForce")
    protected boolean isForce = false;

    @SerializedName("version")
    protected int version;

    @SerializedName("forceable_version")
    protected int forceable_version;

    @SerializedName("message")
    protected String message;

    @SerializedName("title")
    protected String title;

    @SerializedName("display_version")
    protected String displayVersion;

    @SerializedName("change_log")
    protected String changeLog;


    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean force) {
        isForce = force;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getForceable_version() {
        return forceable_version;
    }

    public void setForceable_version(int forceable_version) {
        this.forceable_version = forceable_version;
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
