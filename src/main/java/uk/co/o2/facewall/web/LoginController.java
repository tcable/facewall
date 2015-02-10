package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.LoginFacade;
import uk.co.o2.facewall.facade.OverviewFacade;
import uk.co.o2.facewall.facade.validators.ValidatedUserModel;
import uk.co.o2.facewall.model.OverviewModel;
import uk.co.o2.facewall.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uk.co.o2.facewall.application.Facewall.facewall;

/**
 * Created by tom on 05/02/15.
 */

@Path("/login")
public class LoginController {
    private static final LoginFacade loginFacade = facewall().loginFacade;

    @GET
    public Viewable login() {
        return new Viewable("/login.ftl");
    }

    @POST
    public Response submitLogin(@FormParam("email") String email) {

        if(loginFacade.loginSucceeds(email)) {
            return loginFacade.SuccessfulResponse();
        } else {
            return loginFacade.FailedResponse(email);
        }
    }
}
