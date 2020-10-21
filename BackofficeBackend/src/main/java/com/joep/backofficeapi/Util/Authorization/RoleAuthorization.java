package com.joep.backofficeapi.Util.Authorization;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Stores.UserStores.MongoUserStore;
import com.joep.backofficeapi.Exceptions.UnauthorizedException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RoleAuthorization {
    private static ApplicationUser getUser(HttpServletRequest req) throws Exception {
        String token = req.getHeader("Authorization");
        token = token.substring(7);
        UserStoreContainer container = new UserStoreContainer(new MongoUserStore());
        JwtUtil util = new JwtUtil();
        return container.getUserByName(util.extractUsername(token));
    }
    public static Boolean checkRole(HttpServletRequest req, Roles[] roles) throws Exception {
        ApplicationUser user = getUser(req);
        if (user == null) throw new UnauthorizedException();
        for (Roles role : roles){
            if (user.getRole()== role) return true;
        }
        throw new UnauthorizedException();
    };
    public static Boolean checkRole(HttpServletRequest req, Roles role) throws Exception {
        ApplicationUser user = getUser(req);
        if (user == null) throw new UnauthorizedException();

        if (user.getRole()== role) return true;

        throw new UnauthorizedException();
    };


}
