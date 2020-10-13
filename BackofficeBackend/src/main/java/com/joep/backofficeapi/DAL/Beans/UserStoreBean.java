package com.joep.backofficeapi.DAL.Beans;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Stores.UserStores.MongoUserStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class UserStoreBean {
    @Bean
    UserStoreContainer userStoreContainer() throws UnknownHostException {
        return new UserStoreContainer(new MongoUserStore());
    }
}