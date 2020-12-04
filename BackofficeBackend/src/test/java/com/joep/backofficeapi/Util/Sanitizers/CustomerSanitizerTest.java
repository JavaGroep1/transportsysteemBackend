package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerSanitizerTest {
    @Test
    public void sanitizerFillsInMissingValues(){
        var time = new Date(System.currentTimeMillis());
        //setup
        var request = new EditCustomerRequest();
        var customer= new Customer(null, "name", "identifier","address", "phoneNumber", time);

        //execute
        CustomerSanitizer.sanitize(request,customer);

        //Assert
        assertEquals(customer.getAddress(), request.getAddress());
        assertEquals(customer.getBusinessIdentifier(), request.getBusinessIdentifier());
        assertEquals(customer.getName(), request.getName());
        assertEquals(customer.getPhoneNumber(), request.getPhoneNumber());

    }
    @Test
    public void sanitizerLeavesPopulatesValues(){
        var time = new Date(System.currentTimeMillis());
        //setup
        var request = new EditCustomerRequest("newid", "newname", "newadress", "newnumber");
        var customer= new Customer(null, "name", "identifier","address", "phoneNumber", time);

        //execute
        CustomerSanitizer.sanitize(request,customer);

        //Assert
        assertNotEquals(customer.getAddress(), request.getAddress());
        assertNotEquals(customer.getBusinessIdentifier(), request.getBusinessIdentifier());
        assertNotEquals(customer.getName(), request.getName());
        assertNotEquals(customer.getPhoneNumber(), request.getPhoneNumber());

    }
}