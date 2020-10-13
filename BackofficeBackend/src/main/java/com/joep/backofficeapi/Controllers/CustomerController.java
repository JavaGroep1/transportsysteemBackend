package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Requests.Customer.ChangeCustomerRequest;
import com.joep.backofficeapi.Models.Requests.Customer.getCustomerRequest;
import com.joep.backofficeapi.Models.Requests.Order.ChangeOrderStatusRequest;
import com.joep.backofficeapi.Models.Requests.Order.GetOrderRequest;
import com.joep.backofficeapi.Util.RouteUtility;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class CustomerController {
    @Autowired
    private CustomerContainer customerContainer;

    @PostMapping("/customers/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, HttpServletRequest req) throws InterruptedException, OrderInvalidException, IOException {

        customerContainer.addCustomer(customer);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getOrder(HttpServletRequest request, @RequestBody(required = false) getCustomerRequest data) throws Exception {
        // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});
        if (data == null ) return getAllCustomers();

        if (data.getCustomerId() != null){
          return ResponseEntity.ok(customerContainer.getCustomerById(data.getCustomerId()));

        }
        return getAllCustomers();

    }


    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(customerContainer.getCustomers());
    }

    @PutMapping("/customer/role")
    public ResponseEntity<?> changeCustomerRole(@RequestBody ChangeCustomerRequest request) throws Exception {
        Customer customer = customerContainer.getCustomerById(request.getCustomerId());
        customerContainer.changeCustomerRole(customer,request.getRole());
        return ResponseEntity.ok("Status changed to " + request.getRole().toString());
    }
}
