package com.tapumandal.ecommerce.domain.image;
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
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Image image) {

        getSession().saveOrUpdate(image);
        getSession().flush();
        getSession().clear();
        return image.getId();
    }

    @Override
    public int update(Image image) {

        Optional<Image> tmpEntity = Optional.ofNullable(getById(image.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(image);
            getSession().flush();
            getSession().clear();
        }
        return image.getId();
    }

    @Override
    public List<Image> getAll(Pageable pageable) {


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
        String query = "FROM Image C WHERE C.isDeleted = 0 ORDER BY id DESC";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Image getById(int id) {

        String query = "FROM Image C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (Image) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public Image getImageFirstTime(int id) {

        String query = "FROM Image C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (Image) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Image> getByKeyAndValue(String key, String value) {
        return (List<Image>) getSession().createQuery(
                "from Image where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Image> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Image image = tmpEntity.get();
            image.setActive(false);
            image.setDeleted(true);
            update(image);
            return true;
        }else{
            return false;
        }
    }


}