package uk.co.o2.facewall.facade;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.domain.Person;
import uk.co.o2.facewall.domain.Query;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 05/02/15.
 */
public class LoginFacade {

    public LoginFacade() {

    }

    public boolean loginSucceeds(String email) {
        // check the db for matching email
        // if present return true

//        Query query = Query.newExactQuery(email);
//        List<Person> persons = personRepository.queryPersons(query);

        return true;
    }

    public Response SuccessfulResponse() {
        URI homepage = null;
        try {
            homepage = new URI("/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Response.ResponseBuilder response = Response.seeOther(homepage);
        NewCookie loginCookie = new NewCookie("loggedIn","cookieValue");
        response.cookie(loginCookie);
        return response.build();
    }

    public Response FailedResponse(String email) {
        Map<String, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("error", "Incorrect Email Address.");
        Viewable existing = new Viewable("/login.ftl",model);
        Response.ResponseBuilder response = Response.ok().entity(existing);
        return response.build();
    }
}
