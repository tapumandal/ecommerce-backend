package com.tapumandal.ecommerce.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class SupplierDto {


    @Nullable
    private int id;

    @NotEmpty
    @Size(min=2, max = 50, message = "Select a name to identify")
    private String name;

    @Nullable
    private String contact;

    @Nullable
    private String address;

    @Nullable
    private String note;

    private boolean isActive = true;

    private boolean isDeleted = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

