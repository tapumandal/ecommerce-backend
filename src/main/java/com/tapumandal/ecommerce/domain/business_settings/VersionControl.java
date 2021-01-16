package com.tapumandal.ecommerce.domain.business_settings;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by TapuMandal on 1/8/2021.
 * For any query ask online.tapu@gmail.com
 */
@Entity
@Table(name = "version_control")
@Component
public class VersionControl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name="force")
    private boolean force;

    @Column(name="app_version")
    private Integer appVersion;

    @Column(name="forceable_version")
    private Integer forceableVersion;

    @Column(name="message")
    private String message;

    @Column(name="title")
    private String title;

    @Column(name="display_version")
    private String displayVersion;

    @Column(name="change_log")
    private String changeLog;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public VersionControl() {
    }

    public VersionControl(VersionControlDto versionControlDto) {
        force = versionControlDto.isForce();
        appVersion = versionControlDto.getAppVersion();
        forceableVersion = versionControlDto.getForceableVersion();
        message = versionControlDto.getMessage();
        title = versionControlDto.getTitle();
        displayVersion = versionControlDto.getDisplayVersion();
        changeLog = versionControlDto.getChangeLog();
    }

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
