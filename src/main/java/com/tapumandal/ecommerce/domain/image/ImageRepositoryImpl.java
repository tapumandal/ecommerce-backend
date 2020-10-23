package com.tapumandal.ecommerce.domain.image;
import com.tapumandal.ecommerce.domain.category.Category;
import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.hibernate.SQLQuery;
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
    public Image getImageByName(String name) {

        String query = "FROM Image C WHERE C.name = "+name+" AND C.isDeleted = 0";
        return (Image) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public boolean delete(String name) {

        Optional<Image> tmpEntity = Optional.ofNullable(getImageByName(name));
        if(tmpEntity.isPresent()){
            Image image = tmpEntity.get();
            image.setActive(false);
            image.setDeleted(true);
            return update(image);
        }else{
            return false;
        }
    }

    public boolean update(Image image) {

        try {
            getSession().update(image);
            getSession().flush();
            getSession().clear();
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public List<Image> getImageByProductId(int productId) {

        String sql = "SELECT * FROM image WHERE product_id = :product_id";
        return (List<Image>) getSession().createSQLQuery(sql).addEntity(Image.class).setParameter("product_id", productId).list();

//        return (List<Image>) getSession().createSQLQuery(
//                "from Product where product_id = :value"
//        ).setParameter("value", productId)
//                .getResultList();
    }


}