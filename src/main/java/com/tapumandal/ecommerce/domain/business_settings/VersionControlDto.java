package com.tapumandal.ecommerce.domain.business_settings;

import org.springframework.stereotype.Component;

/**
 * Created by TapuMandal on 1/8/2021.
 * For any query ask online.tapu@gmail.com
 */

@Component
public class VersionControlDto {
    private boolean force;
    private Integer appVersion;
    private Integer forceableVersion;
    private String message;
    private String title;
    private String displayVersion;
    private String changeLog;

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getForceableVersion() {
        return forceableVersion;
    }

    public void setForceableVersion(Integer forceableVersion) {
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
