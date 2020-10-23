package com.tapumandal.ecommerce.domain.appnavigation;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.util.CommonResponseArray;
import com.tapumandal.ecommerce.util.CommonResponseSingle;
import com.tapumandal.ecommerce.util.ControllerHelper;
import com.tapumandal.ecommerce.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/navigation")
public class NavigationController extends ControllerHelper<Navigation> {

    @Autowired
    NavigationService navigationService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createNavigation(@ModelAttribute NavigationDto navigationDto, HttpServletRequest request) {

        System.out.println("CONTROLLER: ");
        System.out.println(new Gson().toJson(navigationDto));

        storeUserDetails(request);
        Navigation navigation = navigationService.create(navigationDto);

        if (navigation != null) {
            return response(true, HttpStatus.CREATED, "New navigation inserted successfully", navigation);
        } else if (navigation == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Navigation) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Navigation) null);
    }

    @GetMapping(path = "/get")
    public CommonResponseSingle<Navigation> getNavigation(HttpServletRequest request) {

        storeUserDetails(request);

        Navigation navigation = navigationService.getNavigation();

        if (navigation != null) {
            return response(true, HttpStatus.FOUND, "Navigation found", navigation);
        } else if (navigation == null) {
            return response(false, HttpStatus.NO_CONTENT, "Navigation not found or deleted", (Navigation) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Navigation) null);
        }
    }

    @PostMapping(path = "/update")
    public CommonResponseSingle updateNavigation(@RequestBody NavigationDto navigationDto, HttpServletRequest request) {

        storeUserDetails(request);

        Navigation navigation = navigationService.update(navigationDto);

        if (navigation != null) {
            return response(true, HttpStatus.OK, "New navigation inserted successfully", navigation);
        } else if (navigation == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Navigation) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Navigation) null);
    }

}