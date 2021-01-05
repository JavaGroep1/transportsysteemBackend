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
import com.joep.backofficeapi.Util.Authorization.RoleAuthorization;
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

    private final CustomerContainer customerContainer;

    private final UserStoreContainer userStoreContainer;

    private RoleAuthorization roleAuthorization;


    public CustomerController(CustomerContainer customerContainer, UserStoreContainer userStoreContainer) {
        this.customerContainer = customerContainer;
        this.userStoreContainer = userStoreContainer;
        this.roleAuthorization = new RoleAuthorization(userStoreContainer);
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<?> addCustomer(HttpServletRequest request, @RequestBody Customer customer, HttpServletRequest req) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        customerContainer.addCustomer(customer);

        return ResponseEntity.ok("ok");
    }

    @GetMapping()
    public ResponseEntity<?> getCustomers(HttpServletRequest request) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

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
    public ResponseEntity<?> DeleteCustomer(HttpServletRequest req, @PathVariable("id") String id) throws Exception {
        roleAuthorization.checkRole(req, new Roles[]{Roles.Admin, Roles.Employee});

        userStoreContainer.deleteAccountByBusinessId(id);
        customerContainer.deleteCustomer(id);
        return ResponseEntity.ok("Deleted");
    }



    @PutMapping()
    public ResponseEntity<?> editCustomer(HttpServletRequest request, @RequestBody EditCustomerRequest editCustomerRequest) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        userStoreContainer.changeRole(editCustomerRequest.getCustomerIdString(), editCustomerRequest.getProspect() ? Roles.Prospect : Roles.Customer);
        userStoreContainer.changeEmail(editCustomerRequest.getCustomerIdString(), editCustomerRequest.getEmail());
        customerContainer.updateCustomer(editCustomerRequest);
        return ResponseEntity.ok("updated");
    }


}
