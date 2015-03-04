package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.SearchFacade;
import uk.co.o2.facewall.model.DefaultSearchResultsModel;
import uk.co.o2.facewall.model.PersonDetailsModel;
import uk.co.o2.facewall.model.SearchResultsModel;
import uk.co.o2.facewall.model.TeamDetailsModel;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.serverError;
import static uk.co.o2.facewall.application.Facewall.facewall;
import static uk.co.o2.facewall.data.datatype.PersonId.newPersonId;
import static uk.co.o2.facewall.domain.Query.newCaseInsensitiveQuery;

@Path("/search")
public class SearchController {

    private final SearchFacade searchFacade = facewall().searchFacade;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;

    @GET
    public Response search(@CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isAuthenticated(loginCookie.getValue())) {
            return Response.ok(new Viewable("/search.ftl")).build();
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

    @GET
    @Path("/results")
    public Response searchResults(@DefaultValue("") @QueryParam("keywords") String keywords, @CookieParam(value = "loggedIn") Cookie loginCookie) {
        if(loginCookie != null && loginCookie.getValue().equals("cookieValue")) {
            final SearchResultsModel searchResults = searchFacade.createSearchResultsModel(newCaseInsensitiveQuery(keywords));
            //TODO: This looks like scala code that has been translated into java. That's fine, but this kind of java code should be avoided if possible. Hopefully we can design it away.
            Response.ResponseBuilder response = serverError();
            if (searchResults instanceof DefaultSearchResultsModel) {
                response = ok(new Viewable("/searchresults.ftl", searchResults));
            } else if (searchResults instanceof PersonDetailsModel) {
                response = ok(new Viewable("/persondetails.ftl", searchResults));
            } else if (searchResults instanceof TeamDetailsModel) {
                response = ok(new Viewable("/teamdetails.ftl", searchResults));
            }
            return response.build();
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
