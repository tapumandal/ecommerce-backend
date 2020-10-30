package com.tapumandal.ecommerce.service;

import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.entity.ProductBusiness;
import com.tapumandal.ecommerce.entity.dto.ProductDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends Service<ProductDto, Product>{

    public List<ProductBusiness> getAllBusiness(Pageable pageable, String category);
}
