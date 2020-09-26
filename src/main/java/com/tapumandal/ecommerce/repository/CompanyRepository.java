package com.tapumandal.ecommerce.repository;

import com.tapumandal.ecommerce.entity.Company;

public interface CompanyRepository extends Repository<Company>{

    public Company getCompanyFirstTime(int companyId);
}
