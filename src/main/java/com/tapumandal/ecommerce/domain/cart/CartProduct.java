package com.tapumandal.ecommerce.domain.cart;

import com.google.gson.annotations.SerializedName;
import com.tapumandal.ecommerce.domain.cart.Cart;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by TapuMandal on 11/7/2020.
 * For any query ask online.tapu@gmail.com
 */

public class CartProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "order_quantity")
    private int orderQuantity = 0;

    @ManyToOne
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}