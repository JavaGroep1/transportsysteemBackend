package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EmployeeController(UserStoreContainer userStoreContainer) {
        this.userStoreContainer = userStoreContainer;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationUser>> getEmployees() {
        List<ApplicationUser> employees = new ArrayList<>();
        employees.addAll(userStoreContainer.getByRole(Roles.Admin));
        employees.addAll(userStoreContainer.getByRole(Roles.Employee));
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Boolean> createEmployee(@RequestBody ApplicationUser employee) throws Exception {
        return ResponseEntity.ok(userStoreContainer.createUser(employee));
    }

    @PutMapping
    public ResponseEntity<ApplicationUser> updateEmployee(@RequestBody EditEmployeeRequest editEmployeeRequest) throws Exception {
        return ResponseEntity.ok(userStoreContainer.updateEmployee(editEmployeeRequest));
    }

    @DeleteMapping(params = "Id")
    public ResponseEntity<?> DeleteCustomer(HttpServletRequest req, String Id) {
        userStoreContainer.deleteUser(new ObjectId(Id));
        return ResponseEntity.ok("Deleted");
    }
}
