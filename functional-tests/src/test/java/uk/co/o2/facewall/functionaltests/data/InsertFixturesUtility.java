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
        //facewallDb.initialise();

            facewallDb.seedFixtures(defaultFixtures()
                            .withTeams(
                                    defaultTeam()
                                            .withProperty("name", "Ecom Shop")
                                            .withMembers(
                                                    defaultPerson()
                                                            .withProperty("name", "Oliver Culley De Lange")
                                                            .withProperty("picture", "https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAC6AAAAJGRmYTFiMDI4LTkyMWItNDdlMy04YWE0LTY1Y2MwOTFkNmZlNw.jpg")
                                                            .withProperty("email", "Oliver.CulleyDeLange@telefonica.com")
                                                            .withProperty("role", "Developer")
                                                            .withProperty("scrum", "Shop")
                                                            .withProperty("password", "olly123")
                                                            .withProperty("details", "Working particularly hard today")
                                                            .withProperty("location", "Bath Road Slough")
                                                            .withProperty("officeLocation", "1EB")
                                            ),
                                    defaultTeam()
                                            .withProperty("name", "The Lab")
                                            .withMembers(
                                                    defaultPerson()
                                                            .withProperty("name", "Fahran Wallace")
                                                            .withProperty("picture", "https://media.licdn.com/mpr/mpr/shrink_200_200/p/4/005/049/331/2aa2d9c.jpg")
                                                            .withProperty("email", "fahren.wallace@o2.com")
                                                            .withProperty("role", "Developer")
                                                            .withProperty("password", "fahran123")
                                                            .withProperty("details", "On a Lab project.")
                                                            .withProperty("location", "Bath Road Slough")
                                                            .withProperty("officeLocation", "Lab Area")
                                            ),
                                    defaultTeam()
                                            .withProperty("name", "Wimbledon")
                                            .withMembers(
                                                    defaultPerson()
                                                            .withProperty("name", "Thomas Holton")
                                                            .withProperty("picture", "https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAADSAAAAJDYyMjEyMDBmLWVjMzktNGRmMi05YmIxLWMzMzg5ZjQ1MGEyNw.png")
                                                            .withProperty("email", "doge@veryemail.com")
                                                            .withProperty("password", "tom123")
                                                            .withProperty("role", "Solution Designer")
                                                            .withProperty("scrum", "Salesforce Integration")
                                                            .withProperty("details", "Working From Air Street Today")
                                                            .withProperty("location", "Bath Road Slough")
                                                            .withProperty("officeLocation", "1EB")
                                            ),
                                    defaultTeam()
                                            .withProperty("name", "FaceWall")
                                            .withMembers(
                                                    defaultPerson()
                                                            .withProperty("name", "Elliot Massey")
                                                            .withProperty("picture", "https://media.licdn.com/mpr/mpr/shrink_200_200/p/7/005/00e/21d/3ee2bb9.jpg")
                                                            .withProperty("email", "elliot@veryemail.com")
                                                            .withProperty("role", "Developer")
                                                            .withProperty("scrum", "FaceWall")
                                                            .withProperty("password", "Pass123")
                                                            .withProperty("details", "Current the only team member full-time on FaceWall.")
                                                            .withProperty("location", "Bath Road Slough")
                                                            .withProperty("officeLocation", "1EE")
                                            ),
                                    defaultTeam()
                                            .withProperty("name", "Priority")
                                            .withMembers(
                                                    defaultPerson()
                                                            .withProperty("name", "Charlie Briggs")
                                                            .withProperty("picture", "https://media.licdn.com/media/p/8/000/2b5/3de/1e3bc39.jpg")
                                            .withProperty("role", "Developer")
                                            .withProperty("scrum", "Web Scrum 3")
                                            .withProperty("password", "Pass1231")
                                            .withProperty("details", "Developing is my PRIORITY")
                                            .withProperty("location", "Air Street London")
                            ),
                    defaultTeam()
                            .withProperty("name","Resourcing and Finance")
                            .withMembers(
                                    defaultPerson()
                                            .withProperty("name", "Tom Cable")
                                            .withProperty("picture", "http://www.ukesf.org/assets/images/companies/sssl/Tom_web.jpg")
                                            .withProperty("role", "Resourcing Expert")
                                            .withProperty("password", "Pass1231")
                                            .withProperty("details", "I am a graduate!")
                                            .withProperty("location", "Bath Road Slough")
                                            .withProperty("officeLocation", "1EE")
                            ),
                    defaultTeam()
                            .withProperty("name","PSN")
                            .withMembers(
                                    defaultPerson()
                                            .withProperty("name", "Luke Langfield")
                                            .withProperty("picture", "https://media.licdn.com/mpr/mpr/shrink_200_200/p/2/005/018/0cc/05d63a1.jpg")
                                            .withProperty("role", "Project Manager")
                                            .withProperty("password", "Pass1231")
                                            .withProperty("details", "I am a graduate!")
                                            .withProperty("location", "Bath Road Slough")
                                            .withProperty("officeLocation", "2EE")
                                    )
            )
            );


    }
}
