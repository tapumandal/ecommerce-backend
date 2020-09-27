package com.tapumandal.ecommerce.util;

import com.tapumandal.ecommerce.entity.*;
import com.tapumandal.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceVerifier {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    MeasurementService measurementService;

    @Autowired
    WarehouseService warehouseService;


    public boolean checkProduct(int id) {
        if(!productService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkUser(int id) {
        if(!userService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkMeasurement(int id) {
        if(!measurementService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkWarehouse(int id) {
        if(!warehouseService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkMeasurements(List<Measurement> measurements) {

        for (Measurement tmpMeas: measurements) {
            if(!checkMeasurement(tmpMeas.getId())){
                return false;
            }
        }
        return true;
    }

//    public boolean checkDeliveryProduct(List<DeliveryProduct> products) {
//        if(!products.isEmpty())
//        for (DeliveryProduct tmpPro: products) {
//
//            if(!checkProduct(tmpPro.getProduct().getId())){
//                System.out.println("checkProduct");
//                return false;
//            }
//
//            if(!checkMeasurement(tmpPro.getMeasurement().getId())){
//                System.out.println("checkMeasurement");
//                return false;
//            }
//
//            if(!checkWarehouse(tmpPro.getWarehouse().getId())){
//                System.out.println("checkWarehouse");
//                return false;
//            }
//        }
//        return true;
//    }

}


