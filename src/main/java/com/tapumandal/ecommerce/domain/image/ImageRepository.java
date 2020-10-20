package com.tapumandal.ecommerce.domain.image;

import com.tapumandal.ecommerce.repository.Repository;

public interface ImageRepository extends Repository<Image> {

    public Image getImageFirstTime(int imageId);
}
