package com.tapumandal.ecommerce.repository.implementation;

import com.tapumandal.ecommerce.entity.Warehouse;
import com.tapumandal.ecommerce.repository.WarehouseRepository;
import com.tapumandal.ecommerce.util.ApplicationPreferences;
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
public class WarehouseRepositoryImpl implements WarehouseRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Warehouse warehouse) {
        getSession().saveOrUpdate(warehouse);
        getSession().flush();
        getSession().clear();
        return warehouse.getId();
    }

    @Override
    public int update(Warehouse warehouse) {

        Optional<Warehouse> tmpEntity = Optional.ofNullable(getById(warehouse.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(warehouse);
            getSession().flush();
            getSession().clear();
        }
        return warehouse.getId();
    }

    @Override
    public List<Warehouse> getAll(Pageable pageable) {


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
        String query = "FROM Warehouse M WHERE M.isDeleted = 0";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Warehouse getById(int id) {

        String query = "FROM Warehouse M WHERE M.id = "+id+" AND M.isDeleted = 0";
        return (Warehouse) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Warehouse> getByKeyAndValue(String key, String value) {
        return (List<Warehouse>) getSession().createQuery(
                "from Warehouse where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Warehouse> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Warehouse warehouse = tmpEntity.get();
            warehouse.setActive(false);
            warehouse.setDeleted(true);
            update(warehouse);
            return true;
        }else{
            return false;
        }
    }

}