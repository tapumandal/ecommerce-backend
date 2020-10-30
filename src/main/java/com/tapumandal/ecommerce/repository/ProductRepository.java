package com.tapumandal.ecommerce.repository;

import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.entity.ProductBusiness;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends Repository<Product>{

    public List<ProductBusiness> getAllBusiness(Pageable pageable, String flag);
}
