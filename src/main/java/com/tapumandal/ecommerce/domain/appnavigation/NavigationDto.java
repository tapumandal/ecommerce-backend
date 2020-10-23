package com.tapumandal.ecommerce.domain.appnavigation;


import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Component
public class NavigationDto {

    private int id;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 50, message = "name field is not OK.")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 50, message = "name field is not OK.")
    private String navigation;

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

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
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
