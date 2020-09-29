package com.joep.springsecurityjwt;

import com.joep.springsecurityjwt.models.*;
import com.joep.springsecurityjwt.util.JwtUtil;
import com.joep.springsecurityjwt.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@CrossOrigin(origins = {"*"})
public class AuthenticationResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    private PasswordEncoder encoder = new PasswordEncoder();

    @GetMapping("/hoi")
    public ResponseEntity<?> test(@RequestBody String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return ResponseEntity.ok(encoder.Encode(text));
    }
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
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String accessToken = jwtTokenUtil.generateToken(userDetails);
        final AuthenticationResponse response = new AuthenticationResponse(accessToken);
        return  ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/authenticate/signup", method = RequestMethod.POST)
    public ResponseEntity<?> CreateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        userDetailService.addUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), authenticationRequest.getEmail(), authenticationRequest.getRoles());
        return ResponseEntity.ok("Created");
    }

    @CrossOrigin(origins = {"*"})
    @GetMapping(value = "/authenticate/getuser")
    public ResponseEntity<?> getUser(HttpServletRequest req) throws Exception {
        String token = req.getHeader("Authorization");
        token = token.substring(7);
        System.out.println(token);
        String username = jwtTokenUtil.extractUsername(token);
        UserInfoResponse user = new UserInfoResponse(userDetailService.loadUserByUsername(username));
        return ResponseEntity.ok(user);
    }
}
