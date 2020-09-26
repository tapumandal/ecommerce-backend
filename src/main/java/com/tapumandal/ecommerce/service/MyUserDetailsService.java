package com.tapumandal.ecommerce.service;

import com.tapumandal.ecommerce.entity.MyUserDetails;
import com.tapumandal.ecommerce.entity.User;
import com.tapumandal.ecommerce.repository.implementation.UserRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepositoryImpl userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getByUserName(username);
        return new MyUserDetails(user);
    }
    
}