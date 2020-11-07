package com.tapumandal.ecommerce.domain.cart;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "cart")
    protected List<CartProduct> cartProducts;

    public Cart(CartDto cartDto){
        this.deliveryCharge = cartDto.getDeliveryCharge();
        this.discountType = cartDto.getDiscountType();
        this.totalDiscount = cartDto.getTotalDiscount();
        this.totalProductPrice = cartDto.getTotalProductPrice();
        this.totalPayable = cartDto.getTotalPayable();
        for (CartProductDto proDto: cartDto.getCartProducts()) {
            CartProduct cartPro = new CartProduct();
            cartPro.setId(proDto.getId());
            cartPro.setProductId(proDto.getProductId());
            cartPro.setOrderQuantity(proDto.getOrderQuantity());
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
}
