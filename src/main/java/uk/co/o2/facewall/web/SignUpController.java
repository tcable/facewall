package uk.co.o2.facewall.web;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.facade.SignUpFacade;
import uk.co.o2.facewall.facade.validators.UserModelValidator;
import uk.co.o2.facewall.facade.validators.ValidatedUserModel;
import uk.co.o2.facewall.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uk.co.o2.facewall.application.Facewall.facewall;

@Path("/register")
public class SignUpController {

    private static final SignUpFacade signUpFacade = facewall().signUpFacade;
    private final UserModelValidator userModelValidator = facewall().userModelValidator;
    private final AccountsFacade accountsFacade = facewall().accountsFacade;
    private String errorStatus = "";

    @GET
    public Response blankSignUpForm(@CookieParam(value = "facewallLoggedIn") Cookie loginCookie) {
        if(loginCookie != null && accountsFacade.isMatching(loginCookie.getValue())) {
            URI homepage = null;
            try {
                homepage = new URI("/");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return Response.seeOther(homepage).build();
        } else {
            final List<String> teamNamesList = signUpFacade.getSortedAvailableTeamNames();
            Map<String, Object> model = new HashMap<>();
            model.put("teamNamesList", teamNamesList);
            //model.put("statusError", errorStatus);
            return Response.ok().entity(new Viewable("/signupform.ftl", model)).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response submitSignUpForm(@FormParam("name") String name,
                                     @FormParam("imgUrl") String imgUrl,
                                     @FormParam("email") String email,
                                     @FormParam("team") String team,
                                     @FormParam("scrum") String scrum,
                                     @FormParam("role") String role,
                                     @FormParam("location") String location) {

        UserModel userModel = new UserModel(email, imgUrl, name, role, location, team, scrum);
        ValidatedUserModel validatedUserModel = userModelValidator.validate(userModel);

        Map<String, Object> model = new HashMap<>();
        model.put("personInformation", validatedUserModel.getPersonInformation());
        model.put("team", validatedUserModel.getTeam());

        if(!accountsFacade.isMatching(email)) {
            if (validatedUserModel.hasErrors()) {
                final List<String> teamNamesList = signUpFacade.getSortedAvailableTeamNames();
                model.put("teamNamesList", teamNamesList);
                model.put("errors", validatedUserModel.getErrors());
                //model.put("statusError", errorStatus);
                Viewable existing = new Viewable("/signupform.ftl", model);
                Response.ResponseBuilder response = Response.ok().entity(existing);
                return response.build();
            } else {
                signUpFacade.registerPerson(validatedUserModel.getPersonInformation(), validatedUserModel.getTeam());
                Viewable existing = new Viewable("/signupsummary.ftl", model);
                NewCookie loginCookie = new NewCookie("facewallLoggedIn", email);
                Response.ResponseBuilder response = Response.ok().entity(existing).cookie(loginCookie);
                return response.build();
            }
        } else {
            final List<String> teamNamesList = signUpFacade.getSortedAvailableTeamNames();
            model.put("teamNamesList", teamNamesList);
            errorStatus = email + " has already been used. Please use a different one.";
            model.put("statusError", errorStatus);
            Viewable register = new Viewable("/signupform.ftl", model);
            Response.ResponseBuilder response = Response.ok().entity(register);
            return response.build();
        }
    }
}
