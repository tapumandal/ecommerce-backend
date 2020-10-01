package com.tapumandal.ecommerce.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class ProductDto {

    @Nullable
    private int id;

    @NotNull(message = "Product name can't be null")
    @Size(min=2, max = 50, message = "Write a proper product name")
    private String name;

    @NotEmpty
    @Size(min=2, max = 50, message = "Product must have a buying price")
    private String buying_price_per_unit;

    @NotEmpty
    @Size(min=2, max = 50, message = "Product must have a selling price")
    private String selling_price_per_unit;

    private boolean active = true;

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

    public String getBuying_price_per_unit() {
        return buying_price_per_unit;
    }

    public void setBuying_price_per_unit(String buying_price_per_unit) {
        this.buying_price_per_unit = buying_price_per_unit;
    }

    public String getSelling_price_per_unit() {
        return selling_price_per_unit;
    }

    public void setSelling_price_per_unit(String selling_price_per_unit) {
        this.selling_price_per_unit = selling_price_per_unit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

