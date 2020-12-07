package com.tapumandal.ecommerce.domain.cart;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.util.CommonResponseArray;
import com.tapumandal.ecommerce.util.CommonResponseSingle;
import com.tapumandal.ecommerce.util.ControllerHelper;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class CartController extends ControllerHelper<Cart> {

    @Autowired
    CartService cartService;

    @PostMapping(path = "/consumer/cart/create")
    public CommonResponseSingle createCart(@RequestBody CartDto cartDto, HttpServletRequest request) {

        System.out.println("CONTROLLER");
        System.out.println(new Gson().toJson(cartDto));

        storeUserDetails(request);

        Cart cart = cartService.create(cartDto);

        if (cart != null) {
            return response(true, HttpStatus.CREATED, "New cart inserted successfully", cart);
        } else if (cart == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Cart) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Cart) null);
    }

    @GetMapping(path = "/cart/{id}")
    public CommonResponseSingle<Cart> getCart(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Cart cart = cartService.getById(id);

        if (cart != null) {
            return response(true, HttpStatus.FOUND, "Cart by id: " + id, cart);
        } else if (cart == null) {
            return response(false, HttpStatus.NO_CONTENT, "Cart not found or deleted", (Cart) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Cart) null);
        }
    }

    @GetMapping(path = "/consumer/{user_id}/cart/list")
    public CommonResponseArray<Cart> getAll(@PathVariable("user_id") int userId, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Cart> carts = cartService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, cartService.getPageable(pageable), pageable);

        if (!carts.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All cart list", carts, myPagenation);
        } else if (carts.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No cart found", new ArrayList<Cart>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Cart>(), myPagenation);
        }

    }


    @PostMapping(path = "/cart/update")
    public CommonResponseSingle updateCart(@RequestBody CartDto cartDto, HttpServletRequest request) {

        System.out.println("CONTROLLER");
        System.out.println(new Gson().toJson(cartDto));

        storeUserDetails(request);

        Cart cart = cartService.update(cartDto);

        if (cart != null) {
            return response(true, HttpStatus.OK, "New cart inserted successfully", cart);
        } else if (cart == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Cart) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Cart) null);
    }

    @DeleteMapping(path = "/cart/{id}")
    public CommonResponseSingle<Cart> deleteCart(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (cartService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Cart by id " + id + " is deleted", (Cart) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Cart not found or deleted", (Cart) null);
        }
    }

}