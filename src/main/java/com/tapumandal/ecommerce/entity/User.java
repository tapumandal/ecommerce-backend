package com.tapumandal.ecommerce.entity;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tapumandal.ecommerce.entity.dto.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "username", unique = true)
    protected String username;

    @Column(name = "username_status")
    protected boolean isUsernameVerified;

    @Column(name = "username_type")
    protected String usernameType;

    @Column(name = "phone")
    protected String phone;

    @Column(name = "password")
    protected String password;

    @Column(name = "address")
    protected String address;

    @Column(name = "work_title")
    protected String workTitle;

    @Column(name = "role")
    protected String role;


    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "company_id")
//    @Where(clause = "company_is_deleted = false AND company_is_active = true")
//    @JsonSerialize(using = CustomCompanySerializer.class)
//    private Company company;

    public User() {
    }

    ;

    public User(UserDto userDto) {

        this.name = userDto.getName();
        this.username = userDto.getUsername();
        this.phone = userDto.getPhone();
        this.password = userDto.getPassword();
        this.address = userDto.getAddress();
        this.workTitle = userDto.getWork_title();
        this.role = userDto.getRole();
        this.isActive = userDto.isActive();
        this.isDeleted = userDto.isDeleted();

//        if (userDto.getCompany() != null) {
//            this.company = new Company(userDto.getCompany());
//        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public boolean isUsernameVerified() {
        return isUsernameVerified;
    }

    public void setUsernameVerified(boolean usernameVerified) {
        isUsernameVerified = usernameVerified;
    }

    public String getUsernameType() {
        return usernameType == null ? "" : usernameType;
    }

    public void setUsernameType(String usernameType) {
        this.usernameType = usernameType;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRole() {
        return role == null ? "" : role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWorkTitle() {
        return workTitle == null ? "" : workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }


}