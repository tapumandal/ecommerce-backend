package com.tapumandal.ecommerce.util;

import com.tapumandal.ecommerce.entity.User;
import com.tapumandal.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.prefs.Preferences;


@Component
public class ApplicationPreferences {

    @Autowired
    UserService userService;

    private static Preferences preferences;

    private static User user;

    public void saveUserByUsername(String username) {
        User u = userService.getByValue("username", username);
        preferences = Preferences.userRoot().node(this.getClass().getName());
        preferences.put("name", u.getName());
        preferences.put("phone", u.getPhone());
        preferences.put("role", u.getRole());

    }

    public static User getUser(){
        user = new User();
        preferences = Preferences.userRoot().node(ApplicationPreferences.class.getName());
        user.setName(preferences.get("name", null));
        user.setPhone(preferences.get("phone", null));
        user.setRole(preferences.get("role", null));
        return user;
    }
}
