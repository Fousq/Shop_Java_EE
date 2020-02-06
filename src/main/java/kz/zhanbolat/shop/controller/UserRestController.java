package kz.zhanbolat.shop.controller;

import kz.zhanbolat.shop.entity.User;
import kz.zhanbolat.shop.service.UserAuthenticationService;
import org.jboss.resteasy.util.Hex;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestController {
    @Inject
    private UserAuthenticationService userAuthenticationService;

    @POST
    @Path("login")
    public void login(User user, HttpSession session) throws NoSuchAlgorithmException {
        boolean authenticated = userAuthenticationService.authenticateUser(user);
        if (authenticated) {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String credential = user.getUsername() + ":" + user.getPassword();
            String token = Hex.encodeHex(messageDigest.digest(credential.getBytes()));
            session.setAttribute("access-token", token);
        }
    }

    @DELETE
    @Path("logout")
    public void logout(HttpSession session) {
        session.removeAttribute("access-token");
    }
}
