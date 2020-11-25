package com.tapumandal.ecommerce.domain.business_settings;
import com.google.gson.Gson;
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
public class BusinessSettingsRepositoryImpl implements BusinessSettingsRepository {

    @Autowired
    EntityManager entityManager;

    private final String modelClassName = "BusinessSettings";


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(BusinessSettings businessSettings) {
        delete();

        getSession().saveOrUpdate(businessSettings);
        getSession().flush();
        getSession().clear();
        return businessSettings.getId();
    }

    @Override
    public BusinessSettings getById(int id) {

        String query = "FROM BusinessSettings";
        return (BusinessSettings) getSession().createQuery(query).uniqueResult();
    }

    private boolean delete(){

        String query = "DELETE FROM BusinessSettings";
//        Query resQuery =  getSession().createQuery(query);
//        resQuery.executeUpdate();
        getSession().createSQLQuery(query);
        getSession().flush();
        getSession().clear();

//        List<BusinessSettings> tmpEntity = getAll(null);
//
//        if (tmpEntity.size()>0) {
//            getSession().delete(tmpEntity);
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }


    @Override
    public int update(BusinessSettings businessSettings) {

        Optional<BusinessSettings> tmpEntity = Optional.ofNullable(getById(businessSettings.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(businessSettings);
            getSession().flush();
            getSession().clear();
        }
        return businessSettings.getId();
    }

    @Override
    public List<BusinessSettings> getAll(Pageable pageable) {


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
        String query = "FROM BusinessSettings ";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }




    @Override
    public List<BusinessSettings> getByKeyAndValue(String key, String value) {
        return (List<BusinessSettings>) getSession().createQuery(
                "from BusinessSettings where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

//        List<BusinessSettings> tmpEntity = getAll(null);
//
//        if (tmpEntity.size()>0) {
//            getSession().delete(tmpEntity);
//            return true;
//        }else{
//            return false;
//        }
        return false;
    }


}