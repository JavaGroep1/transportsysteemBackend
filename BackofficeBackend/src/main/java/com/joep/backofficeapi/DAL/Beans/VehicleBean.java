package com.joep.backofficeapi.DAL.Beans;

import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.DAL.Stores.VehicleStores.MongoVehicleStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class VehicleBean {

    @Bean
    VehicleContainer vehicleContainer() {
        return new VehicleContainer(new MongoVehicleStore());
    }
}