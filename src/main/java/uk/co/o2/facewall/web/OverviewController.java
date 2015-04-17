package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.OverviewFacade;
import uk.co.o2.facewall.model.OverviewModel;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;

import java.net.URI;
import java.net.URISyntaxException;

import static uk.co.o2.facewall.application.Facewall.facewall;

@Path("/")
public class OverviewController {
    private final OverviewFacade overviewFacade = facewall().overviewFacade;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;

    @GET
    public Response overview(@CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
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
