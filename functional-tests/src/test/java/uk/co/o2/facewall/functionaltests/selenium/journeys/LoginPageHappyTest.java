package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import uk.co.o2.facewall.databaseutils.FacewallTestDatabase;
import uk.co.o2.facewall.functionaltests.selenium.common.Configuration;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.neo4j.rest.graphdb.GraphDatabaseFactory.databaseFor;
import static uk.co.o2.facewall.databaseutils.FacewallTestDatabaseFactory.createFacewallTestDatabaseWrappingExistingDatabase;
import static uk.co.o2.facewall.databaseutils.fixture.Fixtures.newFixtures;
import static uk.co.o2.facewall.databaseutils.fixture.TeamDataFactory.defaultTeamWithDefaultMembers;

public class LoginPageHappyTest extends SeleniumBase {

    private static GraphDatabaseService neoDb;
    private static FacewallTestDatabase facewallDb;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public static void beforeClass() {
        if(Configuration.runNeoDb.equals("local")) {
            neoDb = databaseFor("http://localhost:7474/db/data/");
            facewallDb = createFacewallTestDatabaseWrappingExistingDatabase(neoDb);
            facewallDb.clear();
            facewallDb.initialise();
            facewallDb.seedFixtures(newFixtures().withTeams(defaultTeamWithDefaultMembers().withProperty("name", "Ecom Ars")));
        }
    }

    @AfterClass
    public static void afterTest(){
        if(Configuration.runNeoDb.equals("local")) {
            facewallDb.clear();
            facewallDb.initialise();
        }
    }

    @Test
    public void checkLoginPageAcceptsValidEmailAndPassword() {
        // TODO password needs to be implemented

        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        loginPage.enterLoginDetails();

        assertThat(homePage.hasTitle(), is(true));
        assertThat(homePage.hasNavbar(), is(true));
        assertThat(homePage.hasPeople(), is(true));
        assertThat(homePage.peopleHaveImages(), is(true));
    }
    @Test
    public void checkLoginPageBlocksInvalidEmailAndPassword() {
        // TODO password needs to be implemented

        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        loginPage.enterInvalidLoginDetails();

        assertThat(loginPage.hasLoginSubmit(), is(true));
    }
}
