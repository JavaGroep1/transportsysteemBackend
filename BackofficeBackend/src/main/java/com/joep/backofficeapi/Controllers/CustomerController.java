package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.ChangeCustomerRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class CustomerController {

    @Autowired
    private CustomerContainer customerContainer;

    @PostMapping(value = "/customers/add", headers = "Accept=application/json")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, HttpServletRequest req) throws InterruptedException, OrderInvalidException, IOException {
        customerContainer.addCustomer(customer);
        return ResponseEntity.ok("ok");
    }

    @GetMapping( value = "/customers")
    public ResponseEntity<List<Customer>> getCustomer(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(customerContainer.getCustomers());
    }
    @GetMapping( value = "/customers", params = "id")
    public ResponseEntity<Customer> getCustomer(HttpServletRequest request, String id) throws Exception {
        // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});
            return ResponseEntity.ok(customerContainer.getCustomerById(new ObjectId(id)));

    }

    @PutMapping(value = "/customer/role", headers = "Accept=application/json")
    public ResponseEntity<?> changeCustomerRole(@RequestBody ChangeCustomerRequest request) throws Exception {
        Customer customer = customerContainer.getCustomerById(request.getCustomerId());
        customerContainer.changeCustomerRole(customer,request.getRole());
        return ResponseEntity.ok("Status changed to " + request.getRole().toString());
    }
}
