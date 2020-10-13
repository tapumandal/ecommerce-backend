package com.tapumandal.ecommerce.entity;

import com.tapumandal.ecommerce.entity.dto.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    private String name;

//    @Column(name = "image")
//    private MultipartFile image;

    @Column(name = "categories")
    private String categories;

    @Column(name = "description")
    private String description;

    @Column(name = "buyingPricePerUnit")
    private String buyingPricePerUnit;

    @Column(name = "sellingPricePerUnit")
    private String sellingPricePerUnit;

    @Column(name = "discountPrice")
    private String discountPrice;

    @Column(name = "discountTitle")
    private String discountTitle;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unitTitle")
    private String unitTitle;

    @Column(name = "quantity")
    private String quantity;


    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

//    @OneToMany(mappedBy="product", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductImage> productImages = new ArrayList<ProductImage>();


    public Product(ProductDto productDto) {

        this.setId(productDto.getId());
        this.setName(productDto.getName());
//        this.setImage(productDto.getImage());
        this.setCategories(productDto.getCategories());
        this.setDescription(productDto.getDescription());
        this.setBuyingPricePerUnit(productDto.getBuyingPricePerUnit());
        this.setSellingPricePerUnit(productDto.getSellingPricePerUnit());
        this.setDiscountPrice(productDto.getDiscountPrice());
        this.setDiscountTitle(productDto.getDiscountTitle());
        this.setUnit(productDto.getUnit());
        this.setUnitTitle(productDto.getUnitTitle());
        this.setQuantity(productDto.getQuantity());
        this.setActive(productDto.isActive());
        this.setDeleted(productDto.isDelete());
    }

    public Product() {

    }

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

//    public MultipartFile getImage() {
//        return image;
//    }
//
//    public void setImage(MultipartFile image) {
//        this.image = image;
//    }

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

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }


}

