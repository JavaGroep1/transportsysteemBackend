package com.joep.backofficeapi.DAL.Beans;

import com.joep.backofficeapi.DAL.Containers.TicketContainer;
import com.joep.backofficeapi.DAL.Stores.Tickets.MongoTicketStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class TicketBean{

    @Bean
    TicketContainer ticketContainer() {
        return new TicketContainer(new MongoTicketStore());
    }


}