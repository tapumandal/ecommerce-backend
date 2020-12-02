package com.tapumandal.ecommerce.domain.cart;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class CartDto {

    protected int id;

    protected int userId;

    protected int deliveryCharge;

    protected String defaultDiscountBtn = ""; // radioOnProduct/radioSpecialOffer

    protected String selectedDiscountName = ""; //On Product/Special Discount/Mobile Payment/Card Payment

    protected String selectedDiscountType = ""; // TotalPercentage/OverallAmount

    protected String selectedDiscountDetails;

    protected int totalProductDiscount;

    protected int totalProductQuantity;

    protected int totalProductPrice;

    protected int totalDiscount;

    protected int totalPayable;

    protected List<CartProductDto> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDefaultDiscountBtn() {
        return defaultDiscountBtn == null ? "" : defaultDiscountBtn;
    }

    public void setDefaultDiscountBtn(String defaultDiscountBtn) {
        this.defaultDiscountBtn = defaultDiscountBtn;
    }

    public String getSelectedDiscountName() {
        return selectedDiscountName == null ? "" : selectedDiscountName;
    }

    public void setSelectedDiscountName(String selectedDiscountName) {
        this.selectedDiscountName = selectedDiscountName;
    }

    public String getSelectedDiscountType() {
        return selectedDiscountType == null ? "" : selectedDiscountType;
    }

    public void setSelectedDiscountType(String selectedDiscountType) {
        this.selectedDiscountType = selectedDiscountType;
    }

    public String getSelectedDiscountDetails() {
        return selectedDiscountDetails == null ? "" : selectedDiscountDetails;
    }

    public void setSelectedDiscountDetails(String selectedDiscountDetails) {
        this.selectedDiscountDetails = selectedDiscountDetails;
    }

    public int getTotalProductDiscount() {
        return totalProductDiscount;
    }

    public void setTotalProductDiscount(int totalProductDiscount) {
        this.totalProductDiscount = totalProductDiscount;
    }

    public int getTotalProductQuantity() {
        return totalProductQuantity;
    }

    public void setTotalProductQuantity(int totalProductQuantity) {
        this.totalProductQuantity = totalProductQuantity;
    }

    public int getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(int totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
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
}
