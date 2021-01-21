package com.tapumandal.ecommerce.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by TapuMandal on 11/7/2020.
 * For any query ask online.tapu@gmail.com
 */

public class ProductDto {

    @Nullable
    private int id;

//    @NotNull(message = "Product name can't be null")
//    @Size(min=2, max = 50, message = "Write a proper product name")
//    private String name;
//
//    @NotEmpty
//    @Size(min=2, max = 50, message = "Product must have a buying price")
//    private String buying_price_per_unit;
//
//    @NotEmpty
//    @Size(min=2, max = 50, message = "Product must have a selling price")
//    private String selling_price_per_unit;


    @NotNull
    @NotEmpty
    @Size(min=3, max = 50, message = "name field is not OK.")
    private String name;

    //    @NotNull
//    @NotEmpty
//    @Size(min=4, max = 50, message = "image field is not OK.")
    private MultipartFile thumbnail;

    private MultipartFile[] images;

    private String imageRealPath;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 200, message = "categories field is not OK.")
    private String company;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 200, message = "categories field is not OK.")
    private String categories;

    @NotNull
    @NotEmpty
    @Size(min=3, max = 200, message = "Pre selected categories field is not OK.")
    private String[] preSelectedCategories;

    @NotNull
    @NotEmpty
    @Size(min=5, max = 500, message = "description field is not OK.")
    private String description;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 4, message = "buyingPricePerUnit field is not OK.")
    private String buyingPricePerUnit;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 4, message = "sellingPricePerUnit field is not OK.")
    private String sellingPricePerUnit;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 4, message = "discountPrice field is not OK.")
    private String discountPrice;

    @NotNull
    @NotEmpty
    @Size(min=2, max = 20, message = "discountTitle field is not OK.")
    private String discountTitle;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 3, message = "unit field is not OK.")
    private String unit;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 10, message = "unitTitle field is not OK.")
    private String unitTitle;

    @NotNull
    @NotEmpty
    @Size(min=1, max = 3, message = "quantity field is not OK.")
    private String quantity;

    private boolean active = true;

    private boolean isDelete = false;

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

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuyingPricePerUnit() {
        return buyingPricePerUnit;
    }

    public void setBuyingPricePerUnit(String buyingPricePerUnit) {
        this.buyingPricePerUnit = buyingPricePerUnit;
    }

    public String getSellingPricePerUnit() {
        return sellingPricePerUnit;
    }

    public void setSellingPricePerUnit(String sellingPricePerUnit) {
        this.sellingPricePerUnit = sellingPricePerUnit;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getImageRealPath() {
        return imageRealPath;
    }

    public void setImageRealPath(String imageRealPath) {
        this.imageRealPath = imageRealPath;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String[] getPreSelectedCategories() {
        return preSelectedCategories;
    }

    public void setPreSelectedCategories(String[] preSelectedCategories) {
        this.preSelectedCategories = preSelectedCategories;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }
}
