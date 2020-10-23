package com.tapumandal.ecommerce.domain.image;

import com.tapumandal.ecommerce.service.Service;

import java.util.List;

public interface ImageService {

    public Image getImageByName(String name);
    public boolean deleteImageByName(String name);
    public List<Image> getImageByProductId(int productId);
}
