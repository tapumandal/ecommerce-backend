package com.tapumandal.ecommerce.service;

import com.tapumandal.ecommerce.entity.Company;
import com.tapumandal.ecommerce.entity.dto.CompanyDto;

public interface CompanyService extends Service<CompanyDto, Company>{
    boolean isCompanyExist(int id);
}
