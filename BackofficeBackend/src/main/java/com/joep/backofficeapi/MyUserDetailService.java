package com.joep.backofficeapi;


import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Stores.UserStores.MongoUserStore;
import com.joep.backofficeapi.Exceptions.EmailTakenException;
import com.joep.backofficeapi.Exceptions.InvalidEmailException;
import com.joep.backofficeapi.Exceptions.UsernameTakenException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Util.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void addUser(String name, String pass, String Email, String[] roles) throws EmailTakenException, UsernameTakenException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidEmailException {
        if (!isValidEmail(Email)){
            throw new InvalidEmailException();
        }
        if (userStore.emailExists(Email)) {
            throw new EmailTakenException();
        }
        if (userStore.usernameExists(name)) {
            throw new UsernameTakenException();
        }
        String passwordEncoded = encoder.Encode(pass);
        String defaultUserPicture= "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";
        userStore.createUser(new ApplicationUser(name, passwordEncoded, new ArrayList<>(), (int) (System.currentTimeMillis() + 1), Email, roles, defaultUserPicture));
    }

    private boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
