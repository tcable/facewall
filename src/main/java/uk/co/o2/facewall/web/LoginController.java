package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.LoginFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;

import static uk.co.o2.facewall.application.Facewall.facewall;

/**
 * Created by tom on 05/02/15.
 */

@Path("/login")
public class LoginController {
    private static final LoginFacade loginFacade = facewall().loginFacade;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;

    @GET
    public Response login(@CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
            URI homepage = null;
            try {
                homepage = new URI("/");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return Response.seeOther(homepage).build();
        } else {
            return Response.ok().entity(new Viewable("/login.ftl")).build();
        }
    }

    @POST
    public Response submitLogin(@FormParam("email") String email) {
        // , @FormParam("password") String password
        if(loginFacade.loginSucceeds(email)) {
            return loginFacade.SuccessfulResponse(email);
        } else {
            return loginFacade.FailedResponse(email);
        }
    }
}
