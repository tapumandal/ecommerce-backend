package com.tapumandal.ecommerce.controller.v1;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.entity.ProductBusiness;
import com.tapumandal.ecommerce.entity.dto.ProductDto;
import com.tapumandal.ecommerce.service.ProductService;
import com.tapumandal.ecommerce.util.CommonResponseArray;
import com.tapumandal.ecommerce.util.CommonResponseSingle;
import com.tapumandal.ecommerce.util.ControllerHelper;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/product")
public class ProductController extends ControllerHelper<Product> {

    @Autowired
    ProductService productService;


    @PostMapping(path = "/create")
    public ResponseEntity<CommonResponseSingle> createProduct(@ModelAttribute ProductDto productDto, HttpServletRequest request) {

        System.out.println("Controller Create");
        System.out.println(new Gson().toJson(productDto));
        storeUserDetails(request);
        productDto.setImageRealPath(request.getServletContext().getRealPath(""));
        Product product = productService.create(productDto);

        if (product != null) {
            return ResponseEntity.ok(response(true, HttpStatus.CREATED, "New product inserted successfully", product));
        } else if (product == null) {
            return ResponseEntity.ok(response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Product) null));
        }
        return ResponseEntity.ok(response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Product) null));
    }

    @GetMapping(path = "/consumer/{id}")
    public CommonResponseSingle<Product> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Product product = productService.getById(id);

        if (product != null) {
            return response(true, HttpStatus.FOUND, "Product by id: " + id, product);
        } else if (product == null) {
            return response(false, HttpStatus.NO_CONTENT, "Product not found or deleted", (Product) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Product) null);
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<CommonResponseArray<Product>> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Product> products = productService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, productService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(response(true, HttpStatus.FOUND, "All product list", products, myPagenation));
        } else if (products.isEmpty()) {
            return ResponseEntity.ok(response(false, HttpStatus.NO_CONTENT, "No product found", new ArrayList<Product>(), myPagenation));
        } else {
            return ResponseEntity.ok(response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Product>(), myPagenation));
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@ModelAttribute ProductDto productDto, HttpServletRequest request) {

        System.out.println("Controller Update");
        System.out.println(new Gson().toJson(productDto));
        storeUserDetails(request);

        Product product = productService.update(productDto);

        if (product != null) {
            return response(true, HttpStatus.OK, "New product inserted successfully", product);
        } else if (product == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Product) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Product) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Product> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (productService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Product by id " + id + " is deleted", (Product) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Product not found or deleted", (Product) null);
        }
    }


    @GetMapping(path = "/consumer/list/{flag}")
    public ResponseEntity<CommonResponseArray<ProductBusiness>> getAllConsumer(@PathVariable("flag") String flag, HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<ProductBusiness> products = productService.getAllBusiness(pageable, flag);

        MyPagenation myPagenation = managePagenation(request, productService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(responseBusiness(true, HttpStatus.FOUND, "All product list", products, myPagenation));
        } else if (products.isEmpty()) {
            return ResponseEntity.ok(responseBusiness(false, HttpStatus.NO_CONTENT, "No product found", new ArrayList<ProductBusiness>(), myPagenation));
        } else {
            return ResponseEntity.ok(responseBusiness(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<ProductBusiness>(), myPagenation));
        }

    }

    @Autowired
    private   CommonResponseArray commonResponseArray;
    protected  CommonResponseArray responseBusiness(boolean action, HttpStatus status, String message, List<ProductBusiness> data, MyPagenation pagenation){

        commonResponseArray.setAction(action);
        commonResponseArray.setStatus(status);
        commonResponseArray.setMessage(message);
        commonResponseArray.setData(data);
        commonResponseArray.setMyPagenation(pagenation);

        return commonResponseArray;
    }


}
