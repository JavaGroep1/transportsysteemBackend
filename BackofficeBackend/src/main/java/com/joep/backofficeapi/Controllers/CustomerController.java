package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Exceptions.EmailTakenException;
import com.joep.backofficeapi.Exceptions.InvalidEmailException;
import com.joep.backofficeapi.Exceptions.OrderInvalidException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerContainer customerContainer;

    @Autowired
    private UserStoreContainer userStoreContainer;

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, HttpServletRequest req) throws InterruptedException, OrderInvalidException, IOException {
        customerContainer.addCustomer(customer);
        return ResponseEntity.ok("ok");
    }

    @GetMapping()
    public ResponseEntity<?> getCustomers(HttpServletRequest request) throws Exception {
        // RoleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});
        List<ApplicationUser> customers = new ArrayList<>();
        customers.addAll(userStoreContainer.getByRole(Roles.Customer));
        customers.addAll(userStoreContainer.getByRole(Roles.Prospect));
        return ResponseEntity.ok(customers);

    }
  
    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomer(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(customerContainer.getCustomerById(new ObjectId(id)));

    }
      
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> DeleteCustomer(HttpServletRequest req, @PathVariable("id") String id) {
        userStoreContainer.deleteAccountByBusinessId(id);
        customerContainer.deleteCustomer(id);
        return ResponseEntity.ok("Deleted");
    }



    @PutMapping()
    public ResponseEntity<?> editCustomer(@RequestBody EditCustomerRequest editCustomerRequest) throws CustomerNotFoundException, InvalidEmailException, EmailTakenException {
        userStoreContainer.changeRole(editCustomerRequest.getCustomerIdString(), editCustomerRequest.getProspect() ? Roles.Prospect : Roles.Customer);
        userStoreContainer.changeEmail(editCustomerRequest.getCustomerIdString(), editCustomerRequest.getEmail());
        customerContainer.updateCustomer(editCustomerRequest);
        return ResponseEntity.ok("updated");
    }


}
