package com.tapumandal.ecommerce.controller.v1;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.User;
import com.tapumandal.ecommerce.entity.*;
import com.tapumandal.ecommerce.entity.dto.*;
import com.tapumandal.ecommerce.service.MyUserDetailsService;
import com.tapumandal.ecommerce.service.UserService;
import com.tapumandal.ecommerce.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController extends ControllerHelper {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myuserDetailsService;
    @Autowired
    UserDetails userDetails;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseModel> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        userDetails = myuserDetailsService.loadUserByUsername(authenticationRequest.getUsername().toString());

        LoginResponseModel loginResponseModel = new LoginResponseModel();
        loginResponseModel.setJwt(jwtUtil.generateToken(userDetails));
        loginResponseModel.setUser(userService.getUserByUserName(userDetails.getUsername()));

        return ResponseEntity.ok(loginResponseModel);
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>This is the Home Page. </h1> <span>Site is under construction.<span>");
    }

    @PostMapping(path = "/registration")
    public CommonResponseSingle userRegistration(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {

        if (!userService.isUserExist(userDto.getUsername())) {
//            if (userDto.getCompany().getId() != 0) {
//                return response(false, HttpStatus.BAD_REQUEST, "Please check your company information.", (User) null);
//            }
            User user = userService.createUser(userDto);

            if (user != null) {
                return response(true, HttpStatus.CREATED, "User & Company registration successful", user);
            } else {
                return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact.", (User) null);
            }

        } else {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "User already exist", (User) null);
        }
    }

    @PostMapping(path = "/user/create")
    public CommonResponseSingle userCreate(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {

        storeUserDetails(request);

        if (!userService.isUserExist(userDto.getUsername())) {

//            userDto.setCompany(null);
            User user = userService.createUser(userDto);

            if (user != null) {
                return response(true, HttpStatus.CREATED, "User & Company registration successful", user);
            } else {
                return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact.", (User) null);
            }

        } else {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "User already exist", (User) null);
        }
    }

    @GetMapping(path = "user/{id}")
    public CommonResponseSingle<User> getUser(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        User user = userService.getById(id);

        if (user != null) {
            return response(true, HttpStatus.FOUND, "User by id: " + id, user);
        } else if (user == null) {
            return response(false, HttpStatus.NO_CONTENT, "User not found or deleted", (User) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (User) null);
        }
    }

    @GetMapping(path = "user/list")
    public CommonResponseArray getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<User> products = userService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, userService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.OK, "All user list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(true, HttpStatus.NO_CONTENT, "User List is empty", new ArrayList<User>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<User>(), myPagenation);
        }

    }

    @PostMapping(path = "user/update")
    public CommonResponseSingle updateProduct(@RequestBody UserDto userDto, HttpServletRequest request) {

        storeUserDetails(request);

        User user = userService.update(userDto);

        if (user != null) {
            return response(true, HttpStatus.OK, "New user inserted successfully", user);
        } else if (user == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (User) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (User) null);
    }

    @DeleteMapping(path = "user/{id}")
    public CommonResponseSingle<User> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (userService.deleteById(id)) {
            return response(true, HttpStatus.OK, "User by id " + id + " is deleted", (User) null);
        } else {
            return response(false, HttpStatus.NOT_FOUND, "User not found or deleted", (User) null);
        }
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }


    @PostMapping(path = "/consumer/registration")
    public CommonResponseSingle consumerRegistration(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {

        System.out.println("consumerRegistration");
        System.out.println(new Gson().toJson(userDto));

//        if(jwtUtil.validateToken(userDto.getUserTokenId(), userDto.getUsername())){
//            System.out.println("UserName Validate");
//        }
//        System.out.println("extractUsername: "+jwtUtil.extractUsername(userDto.getUserTokenId()));
//
//        System.out.println("extractExpiration: "+jwtUtil.extractExpiration(userDto.getUserTokenId()));


        FirebaseOptions options = null;
        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://grocery-ecommerce-845b8.firebaseio.com/")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);

        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(userDto.getUserTokenId());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        String uid = decodedToken.getUid();
        System.out.println("Firebase Authentication: "+uid);

        if (!userService.isUserExist(userDto.getUsername())) {
//            if (userDto.getCompany().getId() != 0) {
//                return response(false, HttpStatus.BAD_REQUEST, "Please check your company information.", (User) null);
//            }
            User user = userService.createUser(userDto);

            if (user != null) {
                return response(true, HttpStatus.CREATED, "User & Company registration successful", user);
            } else {
                return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact.", (User) null);
            }

        } else {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "User already exist", (User) null);
        }
    }
}