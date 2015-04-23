package uk.co.o2.facewall.functionaltests.data;

import uk.co.o2.facewall.databaseutils.FacewallTestDatabase;
import org.neo4j.graphdb.GraphDatabaseService;

import static uk.co.o2.facewall.databaseutils.FacewallTestDatabaseFactory.createFacewallTestDatabaseWrappingExistingDatabase;
import static uk.co.o2.facewall.databaseutils.fixture.FixturesFactory.defaultFixtures;
import static uk.co.o2.facewall.databaseutils.fixture.PersonDataFactory.defaultPerson;
import static uk.co.o2.facewall.databaseutils.fixture.TeamDataFactory.defaultTeam;
import static org.neo4j.rest.graphdb.GraphDatabaseFactory.databaseFor;

public class InsertFixturesUtility {

    private final static String dbLocation = System.getenv("GRAPHENEDB_URL");
    private final static String localDbLocation = "http://localhost:7474/db/data";

    public static void main(String[] args) {

        GraphDatabaseService neoDb = databaseFor(dbLocation==null ? localDbLocation : dbLocation);
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
                                        .withProperty("email", "hugo@veryemail.com")
                                        .withProperty("role", "Developer")
                                        .withProperty("scrum", "BAU")
                                        .withProperty("password", "hugo123")
                                        .withProperty("details", "Doesn't work here anymore.")
                                        .withProperty("location", "Unknown")
                                        .withProperty("officeLocation", "Unknown")
                                ),
                        defaultTeam()
                                .withProperty("name", "The Lab")
                                .withMembers(
                                        defaultPerson()
                                        .withProperty("name", "Fahran Wallace")
                                        .withProperty("picture", "http://withhomeandgarden.com/wp-content/uploads/2011/01/cat-200x300.jpg")
                                        .withProperty("email", "fahren@veryemail.com")
                                        .withProperty("role", "Developer")
                                        .withProperty("scrum", "Vesta")
                                        .withProperty("password", "fahran123")
                                        .withProperty("details", "On a Lab project.")
                                        .withProperty("location", "Bath Road Slough")
                                        .withProperty("officeLocation", "Lab Area")
                                ),
                        defaultTeam()
                                .withProperty("name", "so special")
                                .withMembers(
                                        defaultPerson()
                                        .withProperty("name", "DOGE")
                                        .withProperty("picture", "http://i.huffpost.com/gen/1451579/thumbs/o-DOGE-facebook.jpg")
                                        .withProperty("email", "doge@veryemail.com")
                                        .withProperty("password", "doge123")
                                        .withProperty("role", "such DOGE")
                                        .withProperty("scrum", "Doge?")
                                        .withProperty("details", "In a bit coin machine.")
                                        .withProperty("location", "Internet")
                                        .withProperty("officeLocation", "Your network port")
                                ),
                        defaultTeam()
                                .withProperty("name", "FaceWall")
                                .withMembers(
                                        defaultPerson()
                                        .withProperty("name", "Elliot Massey")
                                        .withProperty("picture", "http://i.huffpost.com/gen/1451579/thumbs/o-DOGE-facebook.jpg")
                                        .withProperty("email", "elliot@veryemail.com")
                                        .withProperty("role", "Developer")
                                        .withProperty("scrum", "FaceWall")
                                        .withProperty("password", "Pass123")
                                        .withProperty("details", "Current the only team member full-time on FaceWall.")
                                        .withProperty("location", "Bath Road Slough")
                                        .withProperty("officeLocation", "1EE")
                                ),
                        defaultTeam()
                                .withProperty("name", "OPP")
                                .withMembers(
                                        defaultPerson()
                                        .withProperty("name", "Stuart Gray")
                                        .withProperty("picture", "https://2.gravatar.com/avatar/d225c9bb9f57ac91198c9af48b728d4f?d=https%3A%2F%2Fidenticons.github.com%2Fd052c099b06be7c91c32b0ba900c268c.png&r=x&s=440")
                                        .withProperty("scrum", "OPP")
                                        .withProperty("password", "Pass1231")
                                                .withProperty("details", "Blah Blah Blah Blah")
                                                .withProperty("location", "Bath Road Slough")
                                                .withProperty("officeLocation", "2EE")
                                ),
                        defaultTeam()
                                .withProperty("name", "Portal Scrum")
                                .withMembers(
                                        defaultPerson()
                                                .withProperty("name", "Luke Langfield")
                                                .withProperty("picture", "https://pbs.twimg.com/profile_images/1480549921/Board.jpg")
                                                .withProperty("role", "Wannabe Gangsta")
                                                .withProperty("scrum", "FaceWall")
                                                .withProperty("password", "Pass1231")
                                                .withProperty("details", "Blah Blah Blah Blah")
                                                .withProperty("location", "Bath Road Slough")
                                                .withProperty("officeLocation", "2EE"),
                                        defaultPerson()
                                                .withProperty("name", "Charlie Briggs")
                                                .withProperty("picture", "http://hd.wallpaperswide.com/thumbs/adventure_time___jake-t2.jpg"),
                                        defaultPerson()
                                                .withProperty("name", "Tom (Eric) Cable")
                                                .withProperty("picture", "http://i.imgur.com/DC5FsXv.jpg")
                                        .withProperty("scrum", "BAU"),
                                        defaultPerson()
                                        .withProperty("name", "Ecom Test")
                                        .withProperty("picture", "http://i.imgur.com/DC5FsXv.jpg")
                                )
                )
        );
    }
}
