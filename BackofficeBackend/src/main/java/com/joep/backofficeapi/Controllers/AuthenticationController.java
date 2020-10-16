package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Requests.Auth.AuthenticationRequest;
import com.joep.backofficeapi.Models.Requests.Auth.AuthenticationResponse;
import com.joep.backofficeapi.Models.Requests.Auth.UserInfoResponse;
import com.joep.backofficeapi.Util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = {"*"})
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerContainer customerContainer;

    @Autowired
    private UserStoreContainer userStore;

    @Autowired
    private JwtUtil jwtTokenUtil;

    private final PasswordEncoder encoder = new PasswordEncoder();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            String encodedPass = encoder.Encode(authenticationRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), encodedPass)
            );
        }
        catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Username and password combination not found" ,e);
        }
        final ApplicationUser userDetails = userStore.loadUserByUsername(authenticationRequest.getUsername());
        final String accessToken = jwtTokenUtil.generateToken(userDetails);
        final AuthenticationResponse response = new AuthenticationResponse(accessToken);
        return  ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/authenticate/signup", method = RequestMethod.POST)
    public ResponseEntity<?> CreateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        customerContainer.addCustomer(authenticationRequest.getCustomer());
        var user = new ApplicationUser(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword(),
                authenticationRequest.getEmail(),
                authenticationRequest.getRole(),
                authenticationRequest.getCustomer());
        userStore.createUser(user);
        return ResponseEntity.ok("Created");
    }

    @CrossOrigin(origins = {"*"})
    @GetMapping(value = "/authenticate/getuser")
    public ResponseEntity<?> getUser(HttpServletRequest req) throws Exception {
        String token = req.getHeader("Authorization");
        token = token.substring(7);
        System.out.println(token);
        String username = jwtTokenUtil.extractUsername(token);
        UserInfoResponse user = new UserInfoResponse(userStore.loadUserByUsername(username));
        return ResponseEntity.ok(user);
    }
}
