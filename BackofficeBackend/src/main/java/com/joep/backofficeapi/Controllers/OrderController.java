package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order.Order;
import com.joep.backofficeapi.Models.Requests.Order.AddOrderRequest;
import com.joep.backofficeapi.Models.Requests.Order.ChangeOrderStatusRequest;
import com.joep.backofficeapi.Util.Authorization.RoleAuthorization;
import com.joep.backofficeapi.Util.JwtUtil;
import com.joep.backofficeapi.Util.LocationUtility;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/orders")
public class OrderController {

    private final OrderContainer orderContainer;

    private final JwtUtil jwtUtil;
    private final UserStoreContainer userStoreContainer;
    private final VehicleContainer vehicleContainer;
    private final CustomerContainer customerContainer;
    private RoleAuthorization roleAuthorization;

    public OrderController(OrderContainer orderContainer, JwtUtil jwtUtil, UserStoreContainer userStoreContainer, VehicleContainer vehicleContainer, CustomerContainer customerContainer) {
        this.orderContainer = orderContainer;
        this.jwtUtil = jwtUtil;
        this.userStoreContainer = userStoreContainer;
        this.vehicleContainer = vehicleContainer;
        this.customerContainer = customerContainer;
        this.roleAuthorization = new RoleAuthorization(userStoreContainer);

    }


    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderRequest order, HttpServletRequest req) throws Exception {

        var vehicle = vehicleContainer.getVehicleById(order.getVehicleId());
        var customer = userStoreContainer.getUserByName(jwtUtil.extractUsername(req)).getCustomer();

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
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        return ResponseEntity.ok(orderContainer.getOrders());
    }

    @GetMapping(params = "userid")
    public ResponseEntity<List<Order>> getOrdersByUserId(String userid) throws Exception {
        ApplicationUser user = userStoreContainer.getUserById(new ObjectId((userid)));
        Customer customer = customerContainer.getCustomerById(user.getCustomer().getId());
        return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
    }

    @GetMapping(params = "customerid")
    public ResponseEntity<List<Order>> getOrderByCustomerId(HttpServletRequest request, String customerid) throws Exception {
            var customerIdObject = new ObjectId(customerid);
            Customer customer = customerContainer.getCustomerById(customerIdObject);
            return ResponseEntity.ok(orderContainer.getOrdersByCustomer(customer));
    }

    @GetMapping(params = "orderid")
    public ResponseEntity<Order> getOrderByOrderId(HttpServletRequest request, String orderid) throws Exception {
//        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee})

        var orderIdObject = new ObjectId(orderid);
            return ResponseEntity.ok(orderContainer.getOrderById(orderIdObject));
    }

    @GetMapping(value = "/active", params = "userid")
    public ResponseEntity<?> getActiveOrdersById(String userid) throws Exception {
        ApplicationUser user = userStoreContainer.getUserById(new ObjectId((userid)));
        Customer customer = customerContainer.getCustomerById(user.getCustomer().getId());
        return ResponseEntity.ok(orderContainer.getActiveOrdersByCustomer(customer));
    }

    @GetMapping(value = "/active")
    public ResponseEntity<?> getActiveOrders(HttpServletRequest request) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        return ResponseEntity.ok(orderContainer.getActiveOrders());
    }

    @GetMapping(value = "/pending", params = "userid")
    public ResponseEntity<?> getPendingOrderById(String userid) throws Exception {
        ApplicationUser user = userStoreContainer.getUserById(new ObjectId((userid)));
        Customer customer = customerContainer.getCustomerById(user.getCustomer().getId());
        return ResponseEntity.ok(orderContainer.getPendingOrdersByCustomer(customer));
    }

    @GetMapping(value = "/pending")
    public ResponseEntity<?> getPendingOrder(HttpServletRequest request) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

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
    public ResponseEntity<?> deleteOrder(HttpServletRequest request, @PathVariable String id) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        orderContainer.deleteOrder(new ObjectId(id));
        return ResponseEntity.ok("Order deleted");
    }
}
