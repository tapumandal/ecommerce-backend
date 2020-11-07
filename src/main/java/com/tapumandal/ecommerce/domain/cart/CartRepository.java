package com.tapumandal.ecommerce.domain.cart;

import com.tapumandal.ecommerce.repository.Repository;

public interface CartRepository extends Repository<Cart> {

    public Cart getCartFirstTime(int cartId);
}
