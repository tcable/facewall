package controllers;

import facade.SearchFacade;
import model.DefaultSearchResultsModel;
import model.PersonDetailsModel;
import model.SearchResultsModel;
import model.TeamDetailsModel;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static application.Facewall.facewall;
import static domain.Query.newCaseInsensitiveQuery;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.serverError;
import static views.TemplateRenderer.renderTemplate;

@Path("/search")
public class SearchController {

    private final SearchFacade searchFacade = facewall().searchFacade;

    @GET
    public Response search() {
        return ok(renderTemplate("search.ftl")).build();
    }

    @GET
    @Path("/results")
    public Response searchResults(@DefaultValue("") @QueryParam("keywords") String keywords) {
        final SearchResultsModel searchResults = searchFacade.createSearchResultsModel(newCaseInsensitiveQuery(keywords));

        //This looks like scala code that has been translated into java. That's fine, but this kind of java code
        //should be avoided if possible. Hopefully we can design it away.
        Response.ResponseBuilder response = serverError();

        if(searchResults instanceof DefaultSearchResultsModel) {
            Map model = new HashMap<String, SearchResultsModel>() {{
                put("results", searchResults);
            }};

            response = ok(renderTemplate("searchresults.ftl", model));
        } else if(searchResults instanceof PersonDetailsModel) {
            Map model = new HashMap<String, SearchResultsModel>() {{
                put("person", searchResults);
            }};

            response = ok(renderTemplate("persondetails.ftl", model));
        } else if(searchResults instanceof TeamDetailsModel) {
            Map model = new HashMap<String, SearchResultsModel>() {{
                put("team", searchResults);
            }};

            response = ok(renderTemplate("teamdetails.ftl", model));
        }
        return response.build();
    }
}