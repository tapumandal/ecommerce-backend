package com.tapumandal.ecommerce.domain.company;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CompanyRepositoryImpl implements com.tapumandal.ecommerce.domain.company.CompanyRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(com.tapumandal.ecommerce.domain.company.Company company) {

        getSession().saveOrUpdate(company);
        getSession().flush();
        getSession().clear();
        return company.getId();
    }

    @Override
    public int update(com.tapumandal.ecommerce.domain.company.Company company) {

        Optional<com.tapumandal.ecommerce.domain.company.Company> tmpEntity = Optional.ofNullable(getById(company.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(company);
            getSession().flush();
            getSession().clear();
        }
        return company.getId();
    }

    @Override
    public List<com.tapumandal.ecommerce.domain.company.Company> getAll(Pageable pageable) {


        Query resQuery = getQuery();

        int pageNum = pageable.getPageNumber();
        if(pageNum<1){
            pageNum = 1;
        }

        resQuery.setFirstResult((pageNum-1)*pageable.getPageSize());
        resQuery.setMaxResults(pageable.getPageSize());
        return resQuery.getResultList();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        Query resQuery = getQuery();

        MyPagenation myPagenation = new MyPagenation();

        myPagenation.setTotalElement(resQuery.getResultList().size());
        return myPagenation;
    }

    private Query getQuery(){
        String query = "FROM Company C WHERE C.isDeleted = 0";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public com.tapumandal.ecommerce.domain.company.Company getById(int id) {

        String query = "FROM Company C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (com.tapumandal.ecommerce.domain.company.Company) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public com.tapumandal.ecommerce.domain.company.Company getCompanyFirstTime(int id) {

        String query = "FROM Company C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (com.tapumandal.ecommerce.domain.company.Company) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<com.tapumandal.ecommerce.domain.company.Company> getByKeyAndValue(String key, String value) {
        return (List<com.tapumandal.ecommerce.domain.company.Company>) getSession().createQuery(
                "from Company where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<com.tapumandal.ecommerce.domain.company.Company> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            com.tapumandal.ecommerce.domain.company.Company company = tmpEntity.get();
            company.setActive(false);
            company.setDeleted(true);
            update(company);
            return true;
        }else{
            return false;
        }
    }


}