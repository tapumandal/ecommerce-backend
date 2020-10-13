package com.tapumandal.ecommerce.domain.category;

import com.tapumandal.ecommerce.repository.Repository;

public interface CategoryRepository extends Repository<Category> {

    public Category getCompanyFirstTime(int categoryId);
}
