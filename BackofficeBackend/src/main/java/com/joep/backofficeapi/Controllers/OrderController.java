package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Stores.CustomerStores.MongoCustomerStore;
import com.joep.backofficeapi.DAL.Stores.OrderStores.MongoOrderStore;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Orderstatus;
import com.joep.backofficeapi.Models.Requests.ChangeOrderStatusRequest;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@CrossOrigin(origins = {"*"})
public class OrderController {

    private final OrderContainer orderContainer;
    private final CustomerContainer customerContainer;

    public OrderController() {
        this.orderContainer = new OrderContainer(new MongoOrderStore());
        this.customerContainer = new CustomerContainer(new MongoCustomerStore());
    }

    @PostMapping("/orders/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        orderContainer.addOrder(order);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/orders/get")
    public ResponseEntity<?> getOrder(@RequestBody ObjectId id, @RequestBody ObjectId customerId) throws Exception {
        if (id != null){
            return ResponseEntity.ok(orderContainer.getOrderById(id));
        }
        if (customerId != null){
            Customer customer = customerContainer.getCustomerById(customerId);
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }

        return new ResponseEntity<HttpClientErrorException.BadRequest>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderContainer.getOrders());
    }

    @PostMapping("/orders/changestatus")
    public ResponseEntity<?> changeOrderStatus(@RequestBody ChangeOrderStatusRequest request) throws Exception {
        Order order = orderContainer.getOrderById(request.getOrderId());
        orderContainer.changeOrderStatus(order,request.getStatus());
        return ResponseEntity.ok("Status changed to " + request.getStatus().toString());
    }


}
