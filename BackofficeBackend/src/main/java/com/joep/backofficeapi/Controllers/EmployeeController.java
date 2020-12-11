package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import com.joep.backofficeapi.Util.Authorization.RoleAuthorization;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/employees")
public class EmployeeController {

    private UserStoreContainer userStoreContainer;
    private RoleAuthorization roleAuthorization;

    @Autowired
    public EmployeeController(UserStoreContainer userStoreContainer, RoleAuthorization roleAuthorization) {
        this.userStoreContainer = userStoreContainer;
        this.roleAuthorization = roleAuthorization;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationUser>> getEmployees() {
        List<ApplicationUser> employees = new ArrayList<>();
        employees.addAll(userStoreContainer.getByRole(Roles.Admin));
        employees.addAll(userStoreContainer.getByRole(Roles.Employee));
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Boolean> createEmployee(HttpServletRequest req, @RequestBody ApplicationUser employee) throws Exception {
        roleAuthorization.checkRole(req, Roles.Admin);
        try {
            return ResponseEntity.ok(userStoreContainer.createUser(employee));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping
    public ResponseEntity<ApplicationUser> updateEmployee(HttpServletRequest req, @RequestBody EditEmployeeRequest editEmployeeRequest) throws Exception {
        roleAuthorization.checkRole(req, Roles.Admin);
        return ResponseEntity.ok(userStoreContainer.updateEmployee(editEmployeeRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> DeleteCustomer(HttpServletRequest req, @PathVariable("id") String Id) throws Exception {
        roleAuthorization.checkRole(req, Roles.Admin);
        userStoreContainer.deleteUser(new ObjectId(Id));
        return ResponseEntity.ok("Deleted");
    }
}
