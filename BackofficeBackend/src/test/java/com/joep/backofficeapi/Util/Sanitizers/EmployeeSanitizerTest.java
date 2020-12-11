package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeSanitizerTest {
    @Test
    public void sanitizerFillsInMissingValues() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var request = new EditEmployeeRequest();

        var user= new ApplicationUser("username",  "firstName",  "lastName",  "password",  "email", Roles.Employee);

        //execute
        EmployeeSanitizer.sanitize(request,user);

        //Assert
        assertEquals(user.getEmail(), request.getEmail());
        assertEquals(user.getFirstName(), request.getFirstName());
        assertEquals(user.getLastName(), request.getLastName());
        assertEquals(user.getUsername(), request.getUsername());
    }
    @Test
    public void sanitizerLeavesPopulatesValues() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var request = new EditEmployeeRequest("new username", "newfirstname", "newlastname", "newemail");

        var user= new ApplicationUser("username",  "firstName",  "lastName",  "password",  "email", Roles.Employee);

        //execute
        EmployeeSanitizer.sanitize(request,user);

        //Assert
        assertNotEquals(user.getEmail(), request.getEmail());
        assertNotEquals(user.getFirstName(), request.getFirstName());
        assertNotEquals(user.getLastName(), request.getLastName());
        assertNotEquals(user.getUsername(), request.getUsername());

    }
}