package kz.zhanbolat.shop.controller;

import kz.zhanbolat.shop.entity.User;
import kz.zhanbolat.shop.service.UserAuthenticationService;
import org.jboss.resteasy.util.Hex;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestController {
    @Inject
    private UserAuthenticationService userAuthenticationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(User user) throws NoSuchAlgorithmException {
        boolean authenticated = userAuthenticationService.authenticateUser(user);
        if (authenticated) {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String credential = user.getUsername() + ":" + user.getPassword();
            String token = Hex.encodeHex(messageDigest.digest(credential.getBytes()));
            NewCookie cookie = new NewCookie("access-token", token);
            return Response.accepted().cookie(cookie).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("logout")
    public Response logout(@CookieParam("access-token") Cookie cookie) {
        if (cookie.getValue() == null) {
            NewCookie cookieToDelete = new NewCookie(cookie, "cookie about to be deleted", 0, new Date(), false, false);
            return Response.status(Response.Status.BAD_REQUEST).cookie(cookieToDelete).build();
        }
        return Response.ok().build();
    }
}
