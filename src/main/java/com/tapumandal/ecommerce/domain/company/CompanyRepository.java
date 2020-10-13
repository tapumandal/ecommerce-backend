package com.tapumandal.ecommerce.domain.company;

import com.tapumandal.ecommerce.repository.Repository;

public interface CompanyRepository extends Repository<com.tapumandal.ecommerce.domain.company.Company> {

    public com.tapumandal.ecommerce.domain.company.Company getCompanyFirstTime(int companyId);
}
