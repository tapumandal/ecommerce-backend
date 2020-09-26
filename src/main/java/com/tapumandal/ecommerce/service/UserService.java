package com.tapumandal.ecommerce.service;

import com.tapumandal.ecommerce.entity.User;
import com.tapumandal.ecommerce.entity.dto.UserDto;

public interface UserService extends Service<UserDto, User>{

    public boolean isUserExist(String userName);
    public User createUser(UserDto userDto);
    public User getUserByUserName(String username);

}
