package uk.co.o2.facewall.facade;

import org.glassfish.jersey.server.mvc.Viewable;
import uk.co.o2.facewall.data.PersonRepository;
import uk.co.o2.facewall.domain.Person;
import uk.co.o2.facewall.domain.Query;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 05/02/15.
 */
public class AccountsFacade {
    private final PersonRepository personRepository;

    public AccountsFacade(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean isMatching(String email) {
        // check the db for matching email
        // if present return true

        Query query = Query.newExactQuery(email);
        List<Person> persons = personRepository.queryPersonsEmail(query);
        return !persons.isEmpty();
    }
}
