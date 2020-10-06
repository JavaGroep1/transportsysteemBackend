package com.joep.springsecurityjwt.DAL;

import com.joep.springsecurityjwt.models.ApplicationUser;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class MockUserStore implements IUserStore {

    public MockUserStore() {
        Users.add(new ApplicationUser("Username1", "Password1", new ArrayList<>(), 1, "2@gmail.com", null));
        Users.add(new ApplicationUser("Username2", "Password2", new ArrayList<>(), 2, "2@gmail.com", null));
        Users.add(new ApplicationUser("Username3", "Password3", new ArrayList<>(), 3, "2@gmail.com", null));
        Users.add(new ApplicationUser("Username4", "Password4", new ArrayList<>(), 4, "2@gmail.com", null));
        Users.add(new ApplicationUser("Username5", "Password5", new ArrayList<>(), 5, "2@gmail.com", new String[]{"Yee1"}));

    }

    ArrayList<ApplicationUser> Users = new ArrayList<>();

    @Override
    public ArrayList<ApplicationUser> getAllUsers() {
        return Users;
    }

    @Override
    public ApplicationUser getUserByName(String name) {
        for (ApplicationUser User : Users) {
            if (User.getUsername().equals(name))
                return User;
        }
        return null;
    }

    @Override
    public Boolean createUser(ApplicationUser user) {
        Users.add(user);
        return Boolean.TRUE;
    }

    @Override
    public Boolean emailExists(String Email) {
        for (ApplicationUser user : Users)
            if (user.getEmail().equals(Email))
                return true;

        return false;
    }
}
