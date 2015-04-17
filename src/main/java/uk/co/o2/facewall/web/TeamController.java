package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.domain.Query;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.TeamDetailsFacade;
import uk.co.o2.facewall.facade.TeamFacade;
import uk.co.o2.facewall.model.TeamDetailsWithPersonsModel;
import uk.co.o2.facewall.model.TeamListModel;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;

import static uk.co.o2.facewall.application.Facewall.facewall;

@Path("/team")
public class TeamController {

    private final TeamDetailsFacade teamDetailsFacade = facewall().teamDetailsFacade;
    private final TeamFacade teamFacade = facewall().teamFacade;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;

    @GET
    public Response teamList(@CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
            final TeamListModel teamListModel = teamFacade.createTeamListModel();
            return Response.ok().entity(new Viewable("/team.ftl", teamListModel)).build();
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
    @Path("/{teamName}")
    public Response getTeam(@PathParam("teamName") String teamName, @CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
            final TeamDetailsWithPersonsModel team = teamDetailsFacade.createTeamDetailsModel(Query.newExactQuery(teamName));
            if (!team.equals(null)) {
                return Response.ok().entity(new Viewable("/teamdetails.ftl", team)).build();
            } else {
                return Response.ok().entity(new Viewable("/noteam.ftl")).build();
            }
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
