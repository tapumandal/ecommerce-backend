package com.tapumandal.ecommerce.domain.cart;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class CartDto {

    protected int id;

    protected int deliveryCharge;

    protected String discountType; // TotalPercentage/OverallAmount/ProductDiscount

    protected int totalDiscount;

    protected int totalProductPrice;

    protected int totalPayable;

    private boolean isActive = true;
    private boolean isDeleted = false;
    private Date createdAt;
    private Date updatedAt;

    protected int userId;

    protected List<CartProductDto> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public int getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(int totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public int getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(int totalPayable) {
        this.totalPayable = totalPayable;
    }

    public List<CartProductDto> getProductList() {
        return productList;
    }

    public void setProductList(List<CartProductDto> productList) {
        this.productList = productList;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
