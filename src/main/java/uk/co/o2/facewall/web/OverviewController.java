package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.OverviewFacade;
import uk.co.o2.facewall.facade.SearchFacade;
import uk.co.o2.facewall.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.net.URISyntaxException;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.serverError;
import static uk.co.o2.facewall.application.Facewall.facewall;
import static uk.co.o2.facewall.domain.Query.newCaseInsensitiveQuery;

@Path("/")
public class OverviewController {
    private final OverviewFacade overviewFacade = facewall().overviewFacade;
    private final SearchFacade searchFacade = facewall().searchFacade;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;

    @GET
    public Response overview(@CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
            //OverviewModel model = overviewFacade.createOverviewModel();
            //return Response.ok().entity(new Viewable("/overview.ftl", model)).build();
            return Response.ok().entity(new Viewable("/homepage.ftl")).build();
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
    public Response searchResults(@DefaultValue("") @QueryParam("keywords") String keywords, @CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
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
