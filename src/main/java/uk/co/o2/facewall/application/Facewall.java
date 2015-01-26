package uk.co.o2.facewall.application;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import uk.co.o2.facewall.data.DataModule;
import uk.co.o2.facewall.data.JdbcCypherExecutor;
import uk.co.o2.facewall.data.PersonRepository;
import uk.co.o2.facewall.data.TeamRepository;
import uk.co.o2.facewall.data.dao.AdminDAO;
import uk.co.o2.facewall.facade.*;
import uk.co.o2.facewall.facade.modelmapper.OverviewModelMapper;
import uk.co.o2.facewall.facade.modelmapper.PersonDetailsModelMapper;
import uk.co.o2.facewall.facade.modelmapper.SearchResultsModelMapper;
import uk.co.o2.facewall.facade.modelmapper.TeamDetailsModelMapper;
import uk.co.o2.facewall.facade.validators.UserModelValidator;

import static uk.co.o2.facewall.data.DataModule.createDataModule;

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
            SignUpFacade signUpFacade, UserModelValidator userModelValidator) {
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

        String graphene_url = System.getenv("GRAPHENEDB_URL");
//        String graphene_url = "http://app31827831.sb02.stations.graphenedb.com:24789";
        String db_url = graphene_url == null ? "http://localhost:7474/db/data" : graphene_url + "/db/data";
        System.out.println("DB URL: " + db_url);

        try {
            Class.forName("org.neo4j.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JdbcCypherExecutor queryEngine;
        GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(db_url);
        if (graphene_url.isEmpty()) {
            queryEngine = new JdbcCypherExecutor(db_url);
        } else {
            queryEngine = new JdbcCypherExecutor(db_url, "app31827831", "ATzDwxBFhbEniy78dB8L");
        }

//        RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(new RestAPIFacade(db_url));
//        GraphDatabaseService graphDatabaseService = databaseFor(db_url);

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
