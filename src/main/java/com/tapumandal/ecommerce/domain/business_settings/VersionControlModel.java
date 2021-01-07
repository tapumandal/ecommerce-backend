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

    @Column(name = "is_force")
    protected boolean isForce;

    @Column(name = "version")
    protected int version;

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

    @OneToOne(mappedBy = "versionControlModel")
    private BusinessSettings businessSettings;

    public VersionControlModel() {
    }

    public VersionControlModel(VersionControlModelDto versionControlModelDto) {
        this.isForce = versionControlModelDto.isForce();
        this.version = versionControlModelDto.getVersion();
        this.forceableVersion = versionControlModelDto.getForceable_version();
        this.message = versionControlModelDto.getMessage();
        this.title = versionControlModelDto.getTitle();
        this.displayVersion = versionControlModelDto.getDisplayVersion();
        this.changeLog = versionControlModelDto.getChangeLog();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public BusinessSettings getBusinessSettings() {
        return businessSettings;
    }

    public void setBusinessSettings(BusinessSettings businessSettings) {
        this.businessSettings = businessSettings;
    }
}
