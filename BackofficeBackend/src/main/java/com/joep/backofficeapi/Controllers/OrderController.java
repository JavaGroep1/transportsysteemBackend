package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Requests.Order.ChangeOrderStatusRequest;
import com.joep.backofficeapi.Models.Requests.Order.GetOrderRequest;
import com.joep.backofficeapi.Util.RouteUtility;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"*"})
public class OrderController {

    @Autowired
    private OrderContainer orderContainer;

    @Autowired
    private CustomerContainer customerContainer;



    @PostMapping("/orders/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order, HttpServletRequest req) throws Exception {
        String token = req.getHeader("Authorization");
        token = token.substring(7);
        if (order.getCustomer() == null){
            order.setCustomer(customerContainer.getCustomerByJwt(token));
        }

        orderContainer.addOrder(order);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/route")
    public ResponseEntity<?> getRoute() throws IOException, InterruptedException {
        return ResponseEntity.ok(RouteUtility.getRoute("Veltackerstraat 3, diessen", "Professor goossenlaan 1, Tilburg"));
    }
    @GetMapping("/orders")
    public ResponseEntity<?> getOrder(HttpServletRequest request, @Nullable String orderid, @Nullable String customerid) throws Exception {
        ObjectId orderIdObject = null;
        ObjectId customerIdObject = null;
        if (orderid != null){
            orderIdObject = new ObjectId(orderid);
            return ResponseEntity.ok(orderContainer.getOrderById(orderIdObject));
        }
        if (customerid != null){
            customerIdObject = new ObjectId(customerid);
            Customer customer = customerContainer.getCustomerById(customerIdObject);
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }
       // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});
        return getAllOrders();

    }

    @GetMapping("/orders/active")
    public ResponseEntity<?> getActiveOrders(@RequestBody(required = false) GetOrderRequest req) throws Exception {

        if (req != null && req.getCustomerId() != null){
            Customer customer = customerContainer.getCustomerById(req.getCustomerId());
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }
        return ResponseEntity.ok(orderContainer.getActiveOrders());

    }

    @GetMapping("/orders/pending")
    public ResponseEntity<?> getPendingOrder(@RequestBody(required = false) GetOrderRequest req) throws Exception {

        if (req != null && req.getCustomerId() != null){
            Customer customer = customerContainer.getCustomerById(req.getCustomerId());
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
        }
        return ResponseEntity.ok(orderContainer.getPendingOrders());

    }

    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderContainer.getOrders());
    }

    @PostMapping("/orders/status")
    public ResponseEntity<?> changeOrderStatus(@RequestBody ChangeOrderStatusRequest request) throws Exception {
        Order order = orderContainer.getOrderById(request.getOrderId());
        orderContainer.changeOrderStatus(order,request.getStatus());
        return ResponseEntity.ok("Status changed to " + request.getStatus().toString());
    }


}
