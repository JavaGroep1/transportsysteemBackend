package com.joep.springsecurityjwt;

import com.joep.springsecurityjwt.DAL.MockUserStore;
import com.joep.springsecurityjwt.DAL.MongoUserStore;
import com.joep.springsecurityjwt.DAL.UserStoreContainer;
import com.joep.springsecurityjwt.models.ApplicationUser;
import com.joep.springsecurityjwt.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserStoreContainer userStore;
    private PasswordEncoder encoder = new PasswordEncoder();

    public MyUserDetailService() throws UnknownHostException {
        userStore = new UserStoreContainer(new MongoUserStore());
    }

    @Override
    public ApplicationUser loadUserByUsername(String userName) {
        ApplicationUser details = null;
        try {
            details = userStore.getUserByName(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (details == null) throw new UsernameNotFoundException(userName + " Not found");
        return details;
    }

    public void addUser(String name, String pass, String Email, String[] roles) throws Exception {
        if (emailExist(Email)) {
            throw new Exception(
                    "There is an account with that email adress:" + Email);
        }
        String passwordEncoded = encoder.Encode(pass);
        userStore.createUser(new ApplicationUser(name, passwordEncoded, new ArrayList<>(), (int) (System.currentTimeMillis() + 1), Email, roles));
    }

    private Boolean emailExist(String email) {
        return userStore.emailExists(email);
    }
}
