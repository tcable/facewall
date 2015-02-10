package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.OverviewFacade;
import uk.co.o2.facewall.model.OverviewModel;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static uk.co.o2.facewall.application.Facewall.facewall;

@Path("/")
public class OverviewController {
    private final OverviewFacade overviewFacade = facewall().overviewFacade;

    @GET
    public Response overview(@CookieParam(value = "loggedIn") Cookie loginCookie) {
        if(loginCookie != null && loginCookie.getValue().equals("cookieValue")) {
            OverviewModel model = overviewFacade.createOverviewModel();
            return Response.ok().entity(new Viewable("/overview.ftl", model)).build();
        } else {
            URI login = null;
            try {
                login = new URI("/login");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return Response.seeOther(login).build();
        }
    }
}
