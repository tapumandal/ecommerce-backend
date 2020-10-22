package com.tapumandal.ecommerce.domain.image;

import com.tapumandal.ecommerce.repository.Repository;
import org.hibernate.Session;

import java.util.List;

public interface ImageRepository{

    public Session getSession();

    public Image getImageByName(String name);

    public boolean delete(String name);

    public List<Image> getImageByProductId(int productId);
}
