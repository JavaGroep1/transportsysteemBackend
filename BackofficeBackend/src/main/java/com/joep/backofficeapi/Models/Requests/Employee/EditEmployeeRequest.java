package com.joep.backofficeapi.Models.Requests.Employee;

import com.joep.backofficeapi.Models.Authentication.Roles;
import org.bson.types.ObjectId;

public class EditEmployeeRequest {
    private String idString;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Roles role;

    public EditEmployeeRequest(String idString, String username, String firstName, String lastName, String email) {
        this.idString = idString;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EditEmployeeRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
