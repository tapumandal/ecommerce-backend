package com.tapumandal.ecommerce.domain.cart;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class CartProductDto {

    @SerializedName("id")
    protected int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("order_quantity")
    private int orderQuantity;

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
}

