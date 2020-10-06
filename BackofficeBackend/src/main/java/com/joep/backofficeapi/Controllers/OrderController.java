package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Interfaces.IOrderStore;
import com.joep.backofficeapi.DAL.Stores.MongoOrderStore;
import com.joep.backofficeapi.Models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

    private final OrderContainer orderContainer;

    public OrderController() {
        this.orderContainer = new OrderContainer(new MongoOrderStore());
    }

    @PostMapping("/orders/Add")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        orderContainer.addOrder(order);
        return ResponseEntity.ok("ok");
    }
}
