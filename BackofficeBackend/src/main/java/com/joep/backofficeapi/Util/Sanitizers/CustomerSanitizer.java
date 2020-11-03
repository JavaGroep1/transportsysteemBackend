package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import org.apache.commons.lang3.StringUtils;

public class CustomerSanitizer {
    public static void sanitize(EditCustomerRequest customerRequest, Customer customerFromDb) {
        if (StringUtils.isBlank(customerRequest.getAddress())) customerRequest.setAddress(customerFromDb.getAddress());
        if (StringUtils.isBlank(customerRequest.getBusinessIdentifier())) customerRequest.setBusinessIdentifier(customerFromDb.getBusinessIdentifier());
     //      if (customerRequest.getEmail() == null) customerRequest.setEmail(customerFromDb.getEmail());
        if (StringUtils.isBlank(customerRequest.getName())) customerRequest.setName(customerFromDb.getName());
        if (StringUtils.isBlank(customerRequest.getPhoneNumber())) customerRequest.setPhoneNumber(customerFromDb.getPhoneNumber());
    }
}
