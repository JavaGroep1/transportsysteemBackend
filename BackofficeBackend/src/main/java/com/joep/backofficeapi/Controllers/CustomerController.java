package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.ChangeCustomerRequest;
import com.joep.backofficeapi.Models.Requests.Customer.getCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerContainer customerContainer;

    @Autowired
    private UserStoreContainer userStoreContainer;

    @PostMapping("/customers/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, HttpServletRequest req) throws InterruptedException, OrderInvalidException, IOException {

        customerContainer.addCustomer(customer);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers(HttpServletRequest request, @RequestBody(required = false) getCustomerRequest data) throws Exception {
        // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});
        if (data == null ) return getAllCustomers();

        if (data.getCustomerId() != null){
          return ResponseEntity.ok(customerContainer.getCustomerById(data.getCustomerId()));

        }
        return getAllCustomers();

    }

    @DeleteMapping("/customers")
    public ResponseEntity<?> DeleteCustomer(HttpServletRequest req, @RequestBody String businessIdentifier) {
        userStoreContainer.deleteAccount(businessIdentifier);
        customerContainer.deleteCustomer(businessIdentifier);
        return ResponseEntity.ok("Deleted");
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
