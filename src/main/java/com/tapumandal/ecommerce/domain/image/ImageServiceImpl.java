package com.tapumandal.ecommerce.domain.image;

import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    private Image image;

    public ImageServiceImpl(){}

    public ImageServiceImpl(Image image){
        this.image = image;
    }

    @Override
    public Image create(ImageDto imageDto) {

        Image pro = new Image(imageDto);
        Optional<Image> image;

//        try{
            int imageId = imageRepository.create(pro);
            image = Optional.ofNullable(imageRepository.getImageFirstTime(imageId));
//        }catch (Exception e){
//            return null;
//        }

        if(image.isPresent()){
            return image.get();
        }else{
            return null;
        }
    }

    @Override
    public Image update(ImageDto imageDto) {


        Image pro = new Image(imageDto);

        Optional<Image> image;
        try{
            int proId = imageRepository.update(pro);
            image = Optional.ofNullable(imageRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(image.isPresent()){
            return image.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Image> getAll(Pageable pageable) {
        Optional<List<Image>> images = Optional.ofNullable(imageRepository.getAll(pageable));

        if(images.isPresent()){
            return images.get();
        }else{
            return null;
        }
    }

    @Override
    public Image getById(int id) {

        Optional<Image> image = Optional.ofNullable(imageRepository.getById(id));

        if(image.isPresent()){
            return image.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return imageRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Image getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Image> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Image> image = Optional.ofNullable(imageRepository.getById(id));
        if(image.isPresent()){
            if(image.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return image.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

}
