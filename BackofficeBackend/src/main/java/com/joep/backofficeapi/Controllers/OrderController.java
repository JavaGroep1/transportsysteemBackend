package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Exceptions.UserIsNotACustomerException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Autosuggest.Address;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Requests.Order.AddOrderRequest;
import com.joep.backofficeapi.Models.Requests.Order.ChangeOrderStatusRequest;
import com.joep.backofficeapi.Models.Requests.Vehicle.AddVehicleRequest;
import com.joep.backofficeapi.Util.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderContainer orderContainer;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserStoreContainer userStoreContainer;
    @Autowired
    private VehicleContainer vehicleContainer;
    @Autowired
    private CustomerContainer customerContainer;




    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderRequest order, HttpServletRequest req) throws Exception {

        var vehicle = vehicleContainer.getVehicleById(order.getVehicleId());
        var customer = userStoreContainer.getUserByName(jwtUtil.extractUsername(req)).getCustomer();
        //if (customer == null)
           // throw new UserIsNotACustomerException();
        var orderToAdd = new Order(
                order.getDeadline(),
                order.getWeightInKg(),
                order.getStartingPoint(),
                order.getDestination(),
                vehicle,
                customer
        );

        orderContainer.addOrder(orderToAdd);
        return ResponseEntity.ok("ok");
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getOrder(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(orderContainer.getOrders());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) throws Exception {
        ApplicationUser user = userStoreContainer.getUserById(new ObjectId((userId)));
        Customer customer = customerContainer.getCustomerById(user.getCustomer().getId());
        return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
    }
  
    @GetMapping(params = "customerid")
    public ResponseEntity<List<Order>> getOrderByCustomerId(HttpServletRequest request, String customerid) throws Exception {
            var customerIdObject = new ObjectId(customerid);
            Customer customer = customerContainer.getCustomerById(customerIdObject);
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
    }

    @GetMapping(path = "/{orderid}")
    public ResponseEntity<Order> getOrderByOrderId(HttpServletRequest request, @PathVariable("orderid") String orderid) throws Exception {
            var orderIdObject = new ObjectId(orderid);
            return ResponseEntity.ok(orderContainer.getOrderById(orderIdObject));
    }

    @GetMapping(value = "/active/{userId}")
    public ResponseEntity<?> getActiveOrdersById(@PathVariable String userId) throws Exception {
        ApplicationUser user = userStoreContainer.getUserById(new ObjectId((userId)));
        Customer customer = customerContainer.getCustomerById(user.getCustomer().getId());
        return ResponseEntity.ok(orderContainer.getActiveOrdersByCustomer(customer));

    }

    @GetMapping(value = "/active")
    public ResponseEntity<?> getActiveOrders() throws Exception {
        return ResponseEntity.ok(orderContainer.getActiveOrders());
    }

    @GetMapping(value = "/pending/{userId}")
    public ResponseEntity<?> getPendingOrderById(@PathVariable String userId) throws Exception {
        ApplicationUser user = userStoreContainer.getUserById(new ObjectId((userId)));
        Customer customer = customerContainer.getCustomerById(user.getCustomer().getId());
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

    @GetMapping(value = "/location/{address}")
    public ResponseEntity<?> getLocation(@PathVariable String address) throws Exception {
        var location= LocationUtility.getLocation(address);
        assert location != null;
        List<String> addresses = new ArrayList<String>();
        for (var item: location.getItems()) {
            addresses.add(item.getAddress().getLabel());
        }
        System.out.println(addresses);
        return ResponseEntity.ok(addresses);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) throws Exception {
        orderContainer.deleteOrder(new ObjectId(id));
        return ResponseEntity.ok("Order deleted");
    }
}
