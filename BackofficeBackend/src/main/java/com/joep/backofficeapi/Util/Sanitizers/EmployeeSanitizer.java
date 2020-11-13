package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import org.apache.commons.lang3.StringUtils;

public class EmployeeSanitizer {
    public static void sanitize(EditEmployeeRequest employeeRequest, ApplicationUser employeeFromDb) {
        if (StringUtils.isBlank(employeeRequest.getEmail())) employeeRequest.setEmail(employeeFromDb.getEmail());
        if (StringUtils.isBlank(employeeRequest.getFirstName())) employeeRequest.setFirstName(employeeFromDb.getFirstName());
        if (StringUtils.isBlank(employeeRequest.getLastName())) employeeRequest.setLastName(employeeFromDb.getLastName());
        if (StringUtils.isBlank(employeeRequest.getUsername())) employeeRequest.setUsername(employeeFromDb.getUsername());
    }
}
