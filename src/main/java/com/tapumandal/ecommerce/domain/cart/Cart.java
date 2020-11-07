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

    @Column(name = "delivery_charge")
    protected int deliveryCharge;

    @Column(name = "discount_type")
    protected String discountType; // TotalPercentage/OverallAmount/ProductDiscount

    @Column(name = "total_discount")
    protected int totalDiscount;

    @Column(name = "total_product_price")
    protected int totalProductPrice;

    @Column(name = "total_payable")
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
        this.deliveryCharge = cartDto.getDeliveryCharge();
        this.discountType = cartDto.getDiscountType();
        this.totalDiscount = cartDto.getTotalDiscount();
        this.totalProductPrice = cartDto.getTotalProductPrice();
        this.totalPayable = cartDto.getTotalPayable();
        this.isActive = cartDto.isActive();
        this.isDeleted = cartDto.isDeleted();

        this.cartProducts = new ArrayList<CartProduct>();
        for (CartProductDto proDto: cartDto.getProductList()) {
            CartProduct cartPro = new CartProduct();
            cartPro.setId(proDto.getId());
            cartPro.setProductId(proDto.getProductId());
            cartPro.setOrderQuantity(proDto.getOrderQuantity());
            this.isActive = cartDto.isActive();
            this.isDeleted = cartDto.isDeleted();
            cartProducts.add(cartPro);
        }
    }

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

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
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
}
