package com.tapumandal.ecommerce.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.domain.address.Address;
import com.tapumandal.ecommerce.domain.address.AddressDto;
import com.tapumandal.ecommerce.domain.address.AddressRepository;
import com.tapumandal.ecommerce.entity.User;
import com.tapumandal.ecommerce.entity.dto.UserDto;
import com.tapumandal.ecommerce.repository.UserRepository;
import com.tapumandal.ecommerce.service.UserService;
import com.tapumandal.ecommerce.util.ApplicationPreferences;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    private static final String CONSUMER_USER_PASSWORD = "12345abcde!@#$%";
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ApplicationPreferences applicationPreferences;

    private User user;

    public UserServiceImpl(){}

    public UserServiceImpl(User user){
        this.user = user;
    }


    @Override
    public User createUser(UserDto userDto) {

//        True for ecommerce fron app.
//        Make it conditional when mobile app is ready
        if(userDto.getUserTokenId() != null){
            return createUserAccount(userDto);
        }else{
            return createAdminAccount(userDto);
        }
    }

    private User createAdminAccount(UserDto userDto) {

        User u = new User(userDto);
        u.setRole("ADMIN");

        u = this.checkUsernameType(u);


        Optional<User> user;
//        try{
        int userId = userRepository.create(u);

        applicationPreferences.saveUserByUsername(u.getUsername());

        user = Optional.ofNullable(userRepository.getById(userId));
//        }catch (Exception e){
//            return null;
//        }

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    private User createUserAccount(UserDto userDto) {
        User u = new User(userDto);
        u.setPassword(CONSUMER_USER_PASSWORD);
        Optional<User> user;
//        try{
        int userId = userRepository.create(u);

        user = Optional.ofNullable(userRepository.getById(userId));
//        }catch (Exception e){
//            return null;
//        }

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    @Override
    public User create(UserDto u) {
        return null;
    }

    @Override
    public User update(UserDto userDto) {

        List<Address> children = new ArrayList<>();

        User user = getById(userDto.getId());
        System.out.println("SERVICE ADDRESS GET BY ID : "+new Gson().toJson(user));

        for (AddressDto dto : userDto.getAddresses()) {
            Address child;
            if (dto.getId() == 0) {
                //CREATE MODE: create new child
                child = new Address();
//                child.setUser(user); //associate parent
            } else {
                //UPDATE MODE : fetch by id
                child = addressRepository.getById(dto.getId());
            }

            BeanUtils.copyProperties(dto, child);//copy properties from dto
            children.add(child);
        }
        user.getAddresses().clear();
        user.getAddresses().addAll(children);

        Optional<User> userReturn;
        try{
            int userId = userRepository.update(user);
            userReturn = Optional.ofNullable(userRepository.getById(userId));
        }catch (Exception e){
            return null;
        }

        if(userReturn.isPresent()){
            return userReturn.get();
        }else{
            return null;
        }

    }

    @Override
    public List<User> getAll(Pageable pageable) {
        Optional<List<User>> products = Optional.ofNullable(userRepository.getAll(pageable));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }


    @Override
    public User getById(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.getById(id));

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }

    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.getUserByUserName(username));
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return userRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public User getByValue(String key, String value) {
        User user = userRepository.getByKeyAndValue(key, value).get(0);

        return user;
    }

    @Override
    public List<User> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.getById(id));
        if(user.isPresent()){
            if(user.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return user.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return userRepository.getPageable(pageable);
    }

    public boolean isUserExist(String userName){
        return userRepository.isUserExist(userName);
    }

    protected User checkUsernameType(User u){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(u.getUsername());
        if(mat.matches()){
            u.setUsernameType("EMAIL");
        }else{
            u.setUsername(u.getUsername().replace("+88", ""));
            u.setUsername(u.getUsername().replace("88", ""));

//            Pattern pattern2 = Pattern.compile("\\d{11}$");
//            Matcher mat2 = pattern2.matcher(u.getUsername());
//            if(mat.matches()) {
            u.setUsernameType("MOBILE");
//            }
        }
        return u;
    }
}
