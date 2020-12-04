package com.joep.backofficeapi.Util.Authorization;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Stores.UserStores.MongoUserStore;
import com.joep.backofficeapi.Exceptions.UnauthorizedException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RoleAuthorization {


    private final UserStoreContainer store;

    @Autowired
    public RoleAuthorization(UserStoreContainer store) {
        this.store = store;
    }


    public Boolean checkRole(ApplicationUser user, Roles[] roles) throws Exception {
        if (user == null) throw new UnauthorizedException();
        for (Roles role : roles){
            if (user.getRole()== role) return true;
        }
        throw new UnauthorizedException();
    };

    private ApplicationUser getUser(HttpServletRequest req) throws Exception {
        JwtUtil util = new JwtUtil();
        return store.getUserByName(util.extractUsername(req));
    }
    public  Boolean checkRole(HttpServletRequest req, Roles[] roles) throws Exception {
        ApplicationUser user = getUser(req);
        if (user == null) throw new UnauthorizedException();
        for (Roles role : roles){
            if (user.getRole()== role) return true;
        }
        throw new UnauthorizedException();
    };
    public  Boolean checkRole(HttpServletRequest req, Roles role) throws Exception {
        ApplicationUser user = getUser(req);
        if (user == null) throw new UnauthorizedException();

        if (user.getRole()== role) return true;

        throw new UnauthorizedException();
    };



}
