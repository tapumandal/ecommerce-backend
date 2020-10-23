package com.tapumandal.ecommerce.domain.appnavigation;

import com.tapumandal.ecommerce.service.Service;

public interface NavigationService extends Service<NavigationDto, Navigation> {
    public Navigation getNavigation();
}
