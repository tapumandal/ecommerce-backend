package com.tapumandal.ecommerce.domain.cart;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected int userId;

    @Column(name = "image")
    protected int deliveryCharge;

    @Column(name = "company")
    protected String defaultDiscountBtn = ""; // radioOnProduct/radioSpecialOffer

    @Column(name = "categories")
    protected String selectedDiscountName = "On Product/Special Discount/Mobile Payment/Card Payment";

    @Column(name = "pre_selected_categories")
    protected String selectedDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @Column(name = "description")
    protected String selectedDiscountDetails;

    @Column(name = "selling_price_per_unit")
    protected int totalProductDiscount;

    @Column(name = "discount_price")
    protected int totalProductQuantity;

    @Column(name = "discount_title")
    protected int totalProductPrice ;

    @Column(name = "unit")
    protected int totalDiscount;

    @Column(name = "unit_title")
    protected int totalPayable;


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


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    protected List<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(CartDto cartDto){

        this.id = cartDto.getId();
        this.userId = cartDto.getUserId();
        this.deliveryCharge = cartDto.getDeliveryCharge();
        this.defaultDiscountBtn = cartDto.getDefaultDiscountBtn();
        this.selectedDiscountName = cartDto.getSelectedDiscountName();
        this.selectedDiscountType = cartDto.getSelectedDiscountType();
        this.selectedDiscountDetails = cartDto.getSelectedDiscountDetails();
        this.totalProductDiscount = cartDto.getTotalProductDiscount();
        this.totalProductQuantity = cartDto.getTotalProductQuantity();
        this.totalProductPrice = cartDto.getTotalProductPrice();
        this.totalDiscount = cartDto.getTotalDiscount();
        this.totalPayable = cartDto.getTotalPayable();

        this.cartProducts = new ArrayList<CartProduct>();
        if(cartDto.getProductList() != null) {
            for (CartProductDto cartDtoTmp : cartDto.getProductList()) {
                CartProduct cartPro = new CartProduct(cartDtoTmp);
                cartProducts.add(cartPro);
            }
        }
    }

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

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
