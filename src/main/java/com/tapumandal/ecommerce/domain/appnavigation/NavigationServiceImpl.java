package com.tapumandal.ecommerce.domain.appnavigation;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    NavigationRepository navigationRepository;

    private Navigation navigation;

    public NavigationServiceImpl(){}

    public NavigationServiceImpl(Navigation navigation){
        this.navigation = navigation;
    }

    @Override
    public Navigation create(NavigationDto navigationDto) {

        Navigation entity = new Navigation(navigationDto);

        System.out.println("SERVICE: ");
        System.out.println(new Gson().toJson(entity));
//        try{
            return navigationRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
    }

    @Override
    public Navigation update(NavigationDto navigationDto) {


        Navigation entity = new Navigation(navigationDto);

//        try{
            return navigationRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
    }

    @Override
    public Navigation getNavigation() {
        return navigationRepository.getNavigation();
    }

    @Override
    public List<Navigation> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Navigation getById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Navigation getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Navigation> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

}
