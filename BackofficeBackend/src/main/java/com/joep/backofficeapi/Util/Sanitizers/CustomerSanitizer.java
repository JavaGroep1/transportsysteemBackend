package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import org.apache.commons.lang3.StringUtils;

public class CustomerSanitizer {
    public static void sanitize(EditCustomerRequest customerRequest, Customer customerFromDb) {
        if (StringUtils.isEmpty(customerRequest.getAddress())) customerRequest.setAddress(customerFromDb.getAddress());
        if (StringUtils.isEmpty(customerRequest.getBusinessIdentifier())) customerRequest.setBusinessIdentifier(customerFromDb.getBusinessIdentifier());
        if (StringUtils.isEmpty(customerRequest.getName())) customerRequest.setName(customerFromDb.getName());
        if (StringUtils.isEmpty(customerRequest.getPhoneNumber())) customerRequest.setPhoneNumber(customerFromDb.getPhoneNumber());
    }
}
