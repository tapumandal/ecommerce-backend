package com.tapumandal.ecommerce.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.ImageModel;
import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.entity.ProductImage;
import com.tapumandal.ecommerce.entity.dto.ProductDto;
import com.tapumandal.ecommerce.repository.ProductRepository;
import com.tapumandal.ecommerce.service.ProductService;
import com.tapumandal.ecommerce.util.ImageService;
import com.tapumandal.ecommerce.util.MyPagenation;
import com.tapumandal.ecommerce.util.ResourceVerifier;
import com.tapumandal.ecommerce.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ResourceVerifier resourceVerifier;

    @Autowired
    ServiceHelper helper;

    String PRODUCT_FILE_PATH = "/src/public/images/product";

    private Product product;

    public ProductServiceImpl(){}

    public ProductServiceImpl(Product product){
        this.product = product;
    }

    @Override
    public Product create(ProductDto productDto) {

        Product pro = new Product(productDto);

        List<ProductImage> productImages = helper.storeProductImages(PRODUCT_FILE_PATH, productDto.getImages());

        pro.setProductImages(productImages);
        System.out.println("SERVICE: ");
        System.out.println(new Gson().toJson(pro));


        Optional<Product> product;


//        try{
            int productId = productRepository.create(pro);
            product = Optional.ofNullable(productRepository.getById(productId));
//        }catch (Exception e){
//            return null;
//        }

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public Product update(ProductDto productDto) {


        Product pro = new Product(productDto);

        Optional<Product> product;
        try{
            int proId = productRepository.update(pro);
            product = Optional.ofNullable(productRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Product> getAll(Pageable pageable) {
        Optional<List<Product>> products = Optional.ofNullable(productRepository.getAll(pageable));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }

    @Override
    public Product getById(int id) {

        Optional<Product> product = Optional.ofNullable(productRepository.getById(id));

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return productRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Product getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Product> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Product> product = Optional.ofNullable(productRepository.getById(id));
        if(product.isPresent()){
            if(product.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return product.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return productRepository.getPageable(pageable);
    }


}
