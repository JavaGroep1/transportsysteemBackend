package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Requests.Order.ChangeOrderStatusRequest;
import com.joep.backofficeapi.Util.RouteUtility;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderContainer orderContainer;

    @Autowired
    private CustomerContainer customerContainer;

    @PostMapping(value = "/add", headers = "Accept=application/json")
    public ResponseEntity<?> addOrder(@RequestBody Order order, HttpServletRequest req) throws Exception {
        String token = req.getHeader("Authorization");
        token = token.substring(7);
        if (order.getCustomer() == null){
            order.setCustomer(customerContainer.getCustomerByJwt(token));
        }

        orderContainer.addOrder(order);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Order>> getOrder(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(orderContainer.getOrders());
    }
    @GetMapping(value = "", params = "customerid")
    public ResponseEntity<List<Order>> getOrderByCustomerId(HttpServletRequest request, String customerid) throws Exception {
            var customerIdObject = new ObjectId(customerid);
            Customer customer = customerContainer.getCustomerById(customerIdObject);
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
    }

    @GetMapping(value = "", params = "orderid")
    public ResponseEntity<Order> getOrderByOrderId(HttpServletRequest request, String orderid) throws Exception {
            var orderIdObject = new ObjectId(orderid);
            return ResponseEntity.ok(orderContainer.getOrderById(orderIdObject));
    }

    @GetMapping(value = "/active", params = "customerId")
    public ResponseEntity<?> getActiveOrders(String customerId) throws Exception {
            Customer customer = customerContainer.getCustomerById(new ObjectId(customerId));
            return ResponseEntity.ok(orderContainer.getActiveOrdersByCustomer(customer));
    }

    @GetMapping(value = "/active")
    public ResponseEntity<?> getActiveOrders() throws Exception {
        return ResponseEntity.ok(orderContainer.getActiveOrders());
    }

    @GetMapping(value = "/pending", params = "customerId")
    public ResponseEntity<?> getPendingOrder(String customerId) throws Exception {
        Customer customer = customerContainer.getCustomerById(new ObjectId(customerId));
        return ResponseEntity.ok(orderContainer.getPendingOrdersByCustomer(customer));
    }

    @GetMapping(value = "/pending")
    public ResponseEntity<?> getPendingOrder() throws Exception {
        return ResponseEntity.ok(orderContainer.getPendingOrders());
    }


    @PostMapping(value = "/status", headers = "Accept=application/json" )
    public ResponseEntity<?> changeOrderStatus(@RequestBody ChangeOrderStatusRequest request) throws Exception {
        Order order = orderContainer.getOrderById(request.getOrderId());
        orderContainer.changeOrderStatus(order,request.getStatus());
        return ResponseEntity.ok("Status changed to " + request.getStatus().toString());
    }


}
