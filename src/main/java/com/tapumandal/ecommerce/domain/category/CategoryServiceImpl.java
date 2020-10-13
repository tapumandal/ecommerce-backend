package com.tapumandal.ecommerce.domain.category;

import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private Category category;

    public CategoryServiceImpl(){}

    public CategoryServiceImpl(Category category){
        this.category = category;
    }

    @Override
    public Category create(CategoryDto categoryDto) {

        Category pro = new Category(categoryDto);
        Optional<Category> category;

//        try{
            int categoryId = categoryRepository.create(pro);
            category = Optional.ofNullable(categoryRepository.getCompanyFirstTime(categoryId));
//        }catch (Exception e){
//            return null;
//        }

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

    @Override
    public Category update(CategoryDto categoryDto) {


        Category pro = new Category(categoryDto);

        Optional<Category> category;
        try{
            int proId = categoryRepository.update(pro);
            category = Optional.ofNullable(categoryRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Category> getAll(Pageable pageable) {
        Optional<List<Category>> categorys = Optional.ofNullable(categoryRepository.getAll(pageable));

        if(categorys.isPresent()){
            return categorys.get();
        }else{
            return null;
        }
    }

    @Override
    public Category getById(int id) {

        Optional<Category> category = Optional.ofNullable(categoryRepository.getById(id));

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return categoryRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Category getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Category> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.getById(id));
        if(category.isPresent()){
            if(category.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return category.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

}
