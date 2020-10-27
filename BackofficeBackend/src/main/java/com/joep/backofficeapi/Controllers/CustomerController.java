package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.ChangeCustomerRequest;
import com.joep.backofficeapi.Models.Requests.Customer.DeleteCustomerRequest;
import com.joep.backofficeapi.Models.Requests.Customer.getCustomerRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> getCustomers(HttpServletRequest request) throws Exception {
        // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});
        List<ApplicationUser> customers = new ArrayList<>();
        for (ApplicationUser item:
             userStoreContainer.getByRole(Roles.Customer)) {
            customers.add(item);
        }
        for (ApplicationUser item:
                userStoreContainer.getByRole(Roles.Prospect)) {
            customers.add(item);
        }
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> DeleteCustomer(HttpServletRequest req, @PathVariable String id) {
        userStoreContainer.deleteAccount(id);
        customerContainer.deleteCustomer(id);
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

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable ObjectId id) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerContainer.getCustomerById(id));
    }
}
