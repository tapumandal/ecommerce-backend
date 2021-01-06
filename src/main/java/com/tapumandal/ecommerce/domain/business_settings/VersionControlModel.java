package com.tapumandal.ecommerce.domain.business_settings;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by TapuMandal on 11/25/2020.
 * For any query ask online.tapu@gmail.com
 */
@Entity
@Table(name = "version_control_model")
@Component
public class VersionControlModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "isForce")
    protected boolean force;

    @Column(name = "version")
    protected int appVersion;

    @Column(name = "forceable_version")
    protected int forceableVersion;

    @Column(name = "message")
    protected String message;

    @Column(name = "title")
    protected String title;

    @Column(name = "display_version")
    protected String displayVersion;

    @Column(name = "change_log")
    protected String changeLog;

    @Column(name = "created_at")
    @CreationTimestamp
    protected Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    protected Date updatedAt;


    public VersionControlModel() {
    }

    public VersionControlModel(VersionControlModelDto versionControlModelDto) {
        this.force = isForce();
        this.appVersion = getAppVersion();
        this.forceableVersion = getForceableVersion();
        this.message = getMessage();
        this.title = getTitle();
        this.displayVersion = getDisplayVersion();
        this.changeLog = getChangeLog();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

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
