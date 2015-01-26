package uk.co.o2.facewall.functionaltests.data;

import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import uk.co.o2.facewall.databaseutils.FacewallTestDatabase;
import org.neo4j.graphdb.GraphDatabaseService;

import static uk.co.o2.facewall.databaseutils.FacewallTestDatabaseFactory.createFacewallTestDatabaseWrappingExistingDatabase;
import static uk.co.o2.facewall.databaseutils.fixture.FixturesFactory.defaultFixtures;
import static uk.co.o2.facewall.databaseutils.fixture.PersonDataFactory.defaultPerson;
import static uk.co.o2.facewall.databaseutils.fixture.TeamDataFactory.defaultTeam;

public class InsertFixturesUtility {

    private final static String dbLocation = System.getenv("GRAPHENEDB_URL");
    private final static String localDbLocation = "http://localhost:7474/db/data";

    public static void main(String[] args) {

        System.out.println("DB Location: " + dbLocation);
        GraphDatabaseService neoDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbLocation == null ? localDbLocation : dbLocation);
        FacewallTestDatabase facewallDb = createFacewallTestDatabaseWrappingExistingDatabase(neoDb);

        facewallDb.clear();
        facewallDb.initialise();

        facewallDb.seedFixtures(defaultFixtures()
                        .withTeams(
                                defaultTeam()
                                        .withProperty("name", "Ecom Shop")
                                        .withMembers(
                                                defaultPerson()
                                                        .withProperty("name", "Hugo Wainwright")
                                                        .withProperty("picture", "https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-frc1/300430_4471674470606_1745866994_n.jpg")
                                                        .withProperty("role", "Developer")
                                        ),
                                defaultTeam()
                                        .withProperty("name", "Ecom Ars")
                                        .withMembers(
                                                defaultPerson()
                                                        .withProperty("name", "Fahran Wallace")
                                                        .withProperty("picture", "http://withhomeandgarden.com/wp-content/uploads/2011/01/cat-200x300.jpg")
                                        ),
                                defaultTeam()
                                        .withProperty("name", "so special")
                                        .withMembers(
                                                defaultPerson()
                                                        .withProperty("name", "DOGE")
                                                        .withProperty("picture", "http://i.huffpost.com/gen/1451579/thumbs/o-DOGE-facebook.jpg")
                                                        .withProperty("email", "doge@veryemail.com")
                                                        .withProperty("role", "such DOGE")
                                        ),
                                defaultTeam()
                                        .withProperty("name", "OPP")
                                        .withMembers(
                                                defaultPerson()
                                                        .withProperty("name", "Stuart Gray")
                                                        .withProperty("picture", "https://2.gravatar.com/avatar/d225c9bb9f57ac91198c9af48b728d4f?d=https%3A%2F%2Fidenticons.github.com%2Fd052c099b06be7c91c32b0ba900c268c.png&r=x&s=440")
                                        ),
                                defaultTeam()
                                        .withProperty("name", "Portal Scrum")
                                        .withMembers(
                                                defaultPerson()
                                                        .withProperty("name", "Luke Langfield")
                                                        .withProperty("picture", "https://pbs.twimg.com/profile_images/1480549921/Board.jpg")
                                                        .withProperty("role", "Wannabe Gangsta"),
                                                defaultPerson()
                                                        .withProperty("name", "Charlie Briggs")
                                                        .withProperty("picture", "http://hd.wallpaperswide.com/thumbs/adventure_time___jake-t2.jpg"),
                                                defaultPerson()
                                                        .withProperty("name", "Thomas Nathan Cable")
                                                        .withProperty("picture", "http://i.imgur.com/DC5FsXv.jpg")
                                        )
                        )
        );
    }
}
