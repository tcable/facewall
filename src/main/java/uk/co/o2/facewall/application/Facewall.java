package uk.co.o2.facewall.application;

import uk.co.o2.facewall.data.DataModule;
import uk.co.o2.facewall.data.PersonRepository;
import uk.co.o2.facewall.data.TeamRepository;
import uk.co.o2.facewall.data.dao.AdminDAO;
import uk.co.o2.facewall.facade.*;
import uk.co.o2.facewall.facade.modelmapper.*;
import uk.co.o2.facewall.facade.validators.UserModelValidator;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

import static uk.co.o2.facewall.data.DataModule.createDataModule;
import static org.neo4j.rest.graphdb.GraphDatabaseFactory.databaseFor;

final public class Facewall {

    private static Facewall facewall = createFacewall();

    public final OverviewFacade overviewFacade;
    public final SearchFacade searchFacade;
    public final PersonDetailsFacade personDetailsFacade;
    public final TeamDetailsFacade teamDetailsFacade;
    public final TeamFacade teamFacade;
    public final SignUpFacade signUpFacade;
    public final UserModelValidator userModelValidator;

    private Facewall(
            OverviewFacade overviewFacade,
            SearchFacade searchFacade,
            PersonDetailsFacade personDetailsFacade,
            TeamDetailsFacade teamDetailsFacade,
            TeamFacade teamFacade,
            SignUpFacade signUpFacade, UserModelValidator userModelValidator)
    {
        this.overviewFacade = overviewFacade;
        this.searchFacade = searchFacade;
        this.personDetailsFacade = personDetailsFacade;
        this.teamDetailsFacade = teamDetailsFacade;
        this.teamFacade = teamFacade;
        this.signUpFacade = signUpFacade;
        this.userModelValidator = userModelValidator;
    }

    public static Facewall facewall() {
        return facewall;
    }

    private static Facewall createFacewall() {

        RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(new RestAPIFacade("GRAPHENEDB_URL"));
        GraphDatabaseService graphDatabaseService = databaseFor("GRAPHENEDB_URL");

        DataModule dataModule = createDataModule(queryEngine, graphDatabaseService);
        PersonRepository personRepository = dataModule.personRepository;
        TeamRepository teamRepository = dataModule.teamRepository;
        AdminDAO adminDAO = dataModule.adminDAO;

        OverviewFacade overviewFacade = new OverviewFacade(personRepository, new OverviewModelMapper());

        SearchResultsModelMapper searchResultsModelMapper = new SearchResultsModelMapper();
        PersonDetailsModelMapper personDetailsModelMapper = new PersonDetailsModelMapper();
        TeamDetailsModelMapper teamDetailsModelMapper = new TeamDetailsModelMapper();

        PersonDetailsFacade personDetailsFacade = new PersonDetailsFacade(personRepository, personDetailsModelMapper);
        TeamDetailsFacade teamDetailsFacade = new TeamDetailsFacade(teamRepository, teamDetailsModelMapper, new OverviewModelMapper());
        TeamFacade teamFacade = new TeamFacade(teamRepository, teamDetailsModelMapper);

        SearchFacade searchFacade = new SearchFacade(
            personRepository,
            teamRepository,
            searchResultsModelMapper,
            personDetailsModelMapper,
            teamDetailsModelMapper
        );

        SignUpFacade signUpFacade = new SignUpFacade(
                adminDAO,
                personRepository,
                teamRepository);
        UserModelValidator userModelValidator = new UserModelValidator(teamRepository);

        return new Facewall(overviewFacade, searchFacade, personDetailsFacade, teamDetailsFacade, teamFacade, signUpFacade, userModelValidator);
    }
}
