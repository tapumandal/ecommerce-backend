package com.tapumandal.ecommerce.domain.appnavigation;

import com.tapumandal.ecommerce.service.Service;

import java.util.List;

public interface NavigationService extends Service<NavigationDto, Navigation> {
    public List<MenuList> getNavigation();
}
