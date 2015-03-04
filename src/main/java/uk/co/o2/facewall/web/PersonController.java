package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.PersonDetailsFacade;
import uk.co.o2.facewall.model.OverviewModel;
import uk.co.o2.facewall.model.PersonDetailsModel;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static uk.co.o2.facewall.application.Facewall.facewall;
import static uk.co.o2.facewall.data.datatype.PersonId.newPersonId;

@Path("/person")
public class PersonController {

    private final PersonDetailsFacade personDetailsFacade = facewall().personDetailsFacade;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") String id, @CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        final PersonDetailsModel person = personDetailsFacade.createPersonDetailsModel(newPersonId(id));
        if(loginCookie != null && accountsFacade.isAuthenticated(loginCookie.getValue())) {
            return Response.ok().entity(new Viewable("/singleperson.ftl", person)).build();
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
