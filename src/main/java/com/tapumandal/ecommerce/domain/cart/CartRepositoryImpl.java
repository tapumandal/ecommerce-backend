package com.tapumandal.ecommerce.domain.cart;
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
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Cart cart) {

        getSession().saveOrUpdate(cart);
        getSession().flush();
        getSession().clear();
        return cart.getId();
    }

    @Override
    public int update(Cart cart) {

        Optional<Cart> tmpEntity = Optional.ofNullable(getById(cart.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(cart);
            getSession().flush();
            getSession().clear();
        }
        return cart.getId();
    }

    @Override
    public List<Cart> getAll(Pageable pageable) {


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
        String query = "FROM Cart C WHERE C.isDeleted = 0 ORDER BY id DESC";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Cart getById(int id) {

        String query = "FROM Cart C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (Cart) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public Cart getCartFirstTime(int id) {

        String query = "FROM Cart C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (Cart) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Cart> getByKeyAndValue(String key, String value) {
        return (List<Cart>) getSession().createQuery(
                "from Cart where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Cart> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Cart cart = tmpEntity.get();
            cart.setActive(false);
            cart.setDeleted(true);
            update(cart);
            return true;
        }else{
            return false;
        }
    }


}