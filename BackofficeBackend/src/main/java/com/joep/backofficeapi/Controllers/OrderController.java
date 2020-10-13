package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Stores.CustomerStores.MongoCustomerStore;
import com.joep.backofficeapi.DAL.Stores.OrderStores.MongoOrderStore;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Requests.Order.ChangeOrderStatusRequest;
import com.joep.backofficeapi.Models.Requests.Order.GetOrderRequest;
import com.joep.backofficeapi.Util.Authorization.RoleAuthorization;
import com.joep.backofficeapi.Util.RouteUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@CrossOrigin(origins = {"*"})
public class OrderController {

    @Autowired
    private OrderContainer orderContainer;

    @Autowired
    private CustomerContainer customerContainer;



    @PostMapping("/orders/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order, HttpServletRequest req){
        String token = req.getHeader("Authorization");
        token = token.substring(7);

        order.setCustomer(customerContainer.getCustomerByJwt(token));

        orderContainer.addOrder(order);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/route")
    public ResponseEntity<?> getRoute() throws IOException, InterruptedException {
        return ResponseEntity.ok(RouteUtility.getRoute("Veltackerstraat 3, diessen", "Professor goossenlaan 1, Tilburg"));
    }
    @GetMapping("/orders")
    public ResponseEntity<?> getOrder(HttpServletRequest request, @RequestBody GetOrderRequest data) throws Exception {
       // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        if (data.getOrderId() != null){
            return ResponseEntity.ok(orderContainer.getOrderById(data.getOrderId()));
        }
        if (data.getCustomerId() != null){
            Customer customer = customerContainer.getCustomerById(data.getCustomerId());
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }
        return getAllOrders();

    }

    @GetMapping("/orders/active")
    public ResponseEntity<?> getActiveOrders(@RequestBody GetOrderRequest req) throws Exception {

        if (req.getCustomerId() != null){
            Customer customer = customerContainer.getCustomerById(req.getCustomerId());
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }
        return ResponseEntity.ok(orderContainer.getActiveOrders());

    }

    @GetMapping("/orders/pending")
    public ResponseEntity<?> getPendingOrder(@RequestBody GetOrderRequest req) throws Exception {

        if (req.getCustomerId() != null){
            Customer customer = customerContainer.getCustomerById(req.getCustomerId());
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }
        return ResponseEntity.ok(orderContainer.getPendingOrders());

    }

    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderContainer.getOrders());
    }



    @PutMapping("/orders/status")
    public ResponseEntity<?> changeOrderStatus(@RequestBody ChangeOrderStatusRequest request) throws Exception {
        Order order = orderContainer.getOrderById(request.getOrderId());
        orderContainer.changeOrderStatus(order,request.getStatus());
        return ResponseEntity.ok("Status changed to " + request.getStatus().toString());
    }


}
