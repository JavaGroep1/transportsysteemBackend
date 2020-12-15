package com.joep.backofficeapi.DAL.Beans;

import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Stores.OrderStores.MongoOrderStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class OrderBean {

    @Bean
    OrderContainer orderContainer() {
        return new OrderContainer(new MongoOrderStore());
    }
}
