package com.tapumandal.ecommerce.repository;

import com.tapumandal.ecommerce.entity.User;

public interface
UserRepository extends Repository<User>{

    public boolean isUserExist(String userName);
    public User getUserByUserName(String username);

}
