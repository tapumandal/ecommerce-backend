package com.tapumandal.ecommerce.domain.company;

import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements com.tapumandal.ecommerce.domain.company.CompanyService {

    @Autowired
    com.tapumandal.ecommerce.domain.company.CompanyRepository companyRepository;

    private com.tapumandal.ecommerce.domain.company.Company company;

    public CompanyServiceImpl(){}

    public CompanyServiceImpl(com.tapumandal.ecommerce.domain.company.Company company){
        this.company = company;
    }

    @Override
    public com.tapumandal.ecommerce.domain.company.Company create(com.tapumandal.ecommerce.domain.company.CompanyDto companyDto) {

        com.tapumandal.ecommerce.domain.company.Company pro = new com.tapumandal.ecommerce.domain.company.Company(companyDto);
        Optional<com.tapumandal.ecommerce.domain.company.Company> company;

//        try{
            int companyId = companyRepository.create(pro);
            company = Optional.ofNullable(companyRepository.getCompanyFirstTime(companyId));
//        }catch (Exception e){
//            return null;
//        }

        if(company.isPresent()){
            return company.get();
        }else{
            return null;
        }
    }

    @Override
    public com.tapumandal.ecommerce.domain.company.Company update(com.tapumandal.ecommerce.domain.company.CompanyDto companyDto) {


        com.tapumandal.ecommerce.domain.company.Company pro = new com.tapumandal.ecommerce.domain.company.Company(companyDto);

        Optional<com.tapumandal.ecommerce.domain.company.Company> company;
        try{
            int proId = companyRepository.update(pro);
            company = Optional.ofNullable(companyRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(company.isPresent()){
            return company.get();
        }else{
            return null;
        }

    }

    @Override
    public List<com.tapumandal.ecommerce.domain.company.Company> getAll(Pageable pageable) {
        Optional<List<com.tapumandal.ecommerce.domain.company.Company>> companys = Optional.ofNullable(companyRepository.getAll(pageable));

        if(companys.isPresent()){
            return companys.get();
        }else{
            return null;
        }
    }

    @Override
    public com.tapumandal.ecommerce.domain.company.Company getById(int id) {

        Optional<com.tapumandal.ecommerce.domain.company.Company> company = Optional.ofNullable(companyRepository.getById(id));

        if(company.isPresent()){
            return company.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return companyRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public com.tapumandal.ecommerce.domain.company.Company getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<com.tapumandal.ecommerce.domain.company.Company> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<com.tapumandal.ecommerce.domain.company.Company> company = Optional.ofNullable(companyRepository.getById(id));
        if(company.isPresent()){
            if(company.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return company.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

}
