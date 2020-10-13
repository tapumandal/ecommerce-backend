package com.tapumandal.ecommerce.domain.company;

import com.tapumandal.ecommerce.util.CommonResponseArray;
import com.tapumandal.ecommerce.util.CommonResponseSingle;
import com.tapumandal.ecommerce.util.ControllerHelper;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController extends ControllerHelper<com.tapumandal.ecommerce.domain.company.Company> {

    @Autowired
    com.tapumandal.ecommerce.domain.company.CompanyService companyService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createCompany(@RequestBody @Valid com.tapumandal.ecommerce.domain.company.CompanyDto companyDto, HttpServletRequest request) {

        storeUserDetails(request);

        com.tapumandal.ecommerce.domain.company.Company company = companyService.create(companyDto);

        if (company != null) {
            return response(true, HttpStatus.CREATED, "New company inserted successfully", company);
        } else if (company == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (com.tapumandal.ecommerce.domain.company.Company) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (com.tapumandal.ecommerce.domain.company.Company) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<com.tapumandal.ecommerce.domain.company.Company> getCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        com.tapumandal.ecommerce.domain.company.Company company = companyService.getById(id);

        if (company != null) {
            return response(true, HttpStatus.FOUND, "Company by id: " + id, company);
        } else if (company == null) {
            return response(false, HttpStatus.NO_CONTENT, "Company not found or deleted", (com.tapumandal.ecommerce.domain.company.Company) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (com.tapumandal.ecommerce.domain.company.Company) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<com.tapumandal.ecommerce.domain.company.Company> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Company> companys = companyService.getAll(pageable);

//        MyPagenation myPagenation = managePagenation(request, companyService.getPageable(pageable), pageable);

        if (!companys.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All company list", companys);
        } else if (companys.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No company found", new ArrayList<com.tapumandal.ecommerce.domain.company.Company>());
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<com.tapumandal.ecommerce.domain.company.Company>());
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateCompany(@RequestBody com.tapumandal.ecommerce.domain.company.CompanyDto companyDto, HttpServletRequest request) {

        storeUserDetails(request);

        com.tapumandal.ecommerce.domain.company.Company company = companyService.update(companyDto);

        if (company != null) {
            return response(true, HttpStatus.OK, "New company inserted successfully", company);
        } else if (company == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (com.tapumandal.ecommerce.domain.company.Company) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (com.tapumandal.ecommerce.domain.company.Company) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<com.tapumandal.ecommerce.domain.company.Company> deleteCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (companyService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Company by id " + id + " is deleted", (com.tapumandal.ecommerce.domain.company.Company) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Company not found or deleted", (com.tapumandal.ecommerce.domain.company.Company) null);
        }
    }

}