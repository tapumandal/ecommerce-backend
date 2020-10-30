package com.tapumandal.ecommerce.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.domain.image.ImageService;
import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.domain.image.Image;
import com.tapumandal.ecommerce.entity.ProductBusiness;
import com.tapumandal.ecommerce.entity.dto.ProductDto;
import com.tapumandal.ecommerce.repository.ProductRepository;
import com.tapumandal.ecommerce.service.ProductService;
import com.tapumandal.ecommerce.util.MyPagenation;
import com.tapumandal.ecommerce.util.ResourceVerifier;
import com.tapumandal.ecommerce.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Autowired
    ImageService imageService;

    String PRODUCT_FILE_PATH = "public/images/product/";

    private Product product;

    public ProductServiceImpl(){}

    public ProductServiceImpl(Product product){
        this.product = product;
    }

    @Override
    public Product create(ProductDto productDto) {

        PRODUCT_FILE_PATH = System.getProperty("user.dir")+PRODUCT_FILE_PATH;

        Product pro = new Product(productDto);

        if(productDto.getImages().length > 0) {
            List<Image> productImages = helper.storeProductImages(productDto.getImages());
            String thumbnailUrl = productImages.get(0).getUrl().replaceAll(productImages.get(0).getName(), "thumbnail." + productImages.get(0).getName());
            pro.setImage(thumbnailUrl);
            pro.setProductImages(productImages);
        }

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

        if(productDto.getImages() != null) {
            List<Image> productImages = helper.storeProductImages(productDto.getImages());
            String thumbnailUrl = productImages.get(0).getUrl().replaceAll(productImages.get(0).getName(), "thumbnail." + productImages.get(0).getName());
            pro.setImage(thumbnailUrl);

            List<Image> existingImages = imageService.getImageByProductId(pro.getId());
            System.out.println("HHHHHHHHHHH");
            System.out.println(new Gson().toJson(existingImages));
            productImages.addAll(existingImages);
            pro.setProductImages(productImages);
        }

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


    @Override
    public List<ProductBusiness> getAllBusiness(Pageable pageable, String flag) {
        Optional<List<ProductBusiness>> products = Optional.ofNullable(productRepository.getAllBusiness(pageable, flag));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }
}
