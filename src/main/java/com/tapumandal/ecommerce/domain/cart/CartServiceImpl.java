package com.tapumandal.ecommerce.domain.cart;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    private Cart cart;

    public CartServiceImpl(){}

    public CartServiceImpl(Cart cart){
        this.cart = cart;
    }

    @Override
    public Cart create(CartDto cartDto) {

        Cart pro = new Cart(cartDto);

        System.out.println("SERVICE");
        System.out.println(new Gson().toJson(pro));

        Optional<Cart> cart;

//        try{
            int cartId = cartRepository.create(pro);
            cart = Optional.ofNullable(cartRepository.getCartFirstTime(cartId));
//        }catch (Exception e){
//            return null;
//        }

        if(cart.isPresent()){
            return cart.get();
        }else{
            return null;
        }
    }

    @Override
    public Cart update(CartDto cartDto) {

        Cart pro = new Cart(cartDto);

        System.out.println("SERVICE");
        System.out.println(new Gson().toJson(pro));

        Optional<Cart> cart;
//        try{
            Cart tmpCart = cartRepository.getById(pro.getId());
            pro.setCartProducts(tmpCart.getCartProducts());
            int proId = cartRepository.update(pro);
            cart = Optional.ofNullable(cartRepository.getById(proId));
//        }catch (Exception e){
//            return null;
//        }

        if(cart.isPresent()){
            return cart.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Cart> getAll(Pageable pageable) {
        Optional<List<Cart>> carts = Optional.ofNullable(cartRepository.getAll(pageable));

        if(carts.isPresent()){
            return carts.get();
        }else{
            return null;
        }
    }

    @Override
    public Cart getById(int id) {

        Optional<Cart> cart = Optional.ofNullable(cartRepository.getById(id));

        if(cart.isPresent()){
            return cart.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
//        try {
            return cartRepository.delete(id);
//        }catch (Exception ex){
//            return false;
//        }
    }

    @Override
    public Cart getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Cart> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.getById(id));
        if(cart.isPresent()){
            if(cart.get().isActive()){
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

}
