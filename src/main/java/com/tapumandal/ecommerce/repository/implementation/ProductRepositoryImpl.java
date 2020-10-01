package com.tapumandal.ecommerce.repository.implementation;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.repository.ProductRepository;
import com.tapumandal.ecommerce.util.ApplicationPreferences;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Product product) {

        getSession().saveOrUpdate(product);
        getSession().flush();
        getSession().clear();

        System.out.println("Repository: ");
        System.out.println(new Gson().toJson(product));
        return product.getId();
    }

    @Override
    public int update(Product product) {

        Optional<Product> tmpEntity = Optional.ofNullable(getById(product.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(product);
            getSession().flush();
            getSession().clear();
        }
        return product.getId();
    }

    @Override
    public List<Product> getAll(Pageable pageable) {


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
        String query = "FROM Product P WHERE P.isDeleted = 0";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Product getById(int id) {

        String query = "FROM Product P WHERE P.id = "+id+" AND P.isDeleted = 0";
        return (Product) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Product> getByKeyAndValue(String key, String value) {
        return (List<Product>) getSession().createQuery(
                "from Product where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Product> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Product product = tmpEntity.get();
            product.setActive(false);
            product.setDeleted(true);
            update(product);
            return true;
        }else{
            return false;
        }
    }


}