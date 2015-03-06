package uk.co.o2.facewall.functionaltests.selenium.journeys;

import uk.co.o2.facewall.databaseutils.FacewallTestDatabase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import uk.co.o2.facewall.functionaltests.selenium.common.Configuration;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;

import static uk.co.o2.facewall.databaseutils.FacewallTestDatabaseFactory.createFacewallTestDatabaseWrappingExistingDatabase;
import static uk.co.o2.facewall.databaseutils.fixture.Fixtures.newFixtures;
import static uk.co.o2.facewall.databaseutils.fixture.TeamDataFactory.defaultTeamWithDefaultMembers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.neo4j.rest.graphdb.GraphDatabaseFactory.databaseFor;

public class HomePageTest extends SeleniumBase {

    private static GraphDatabaseService neoDb;
    private static FacewallTestDatabase facewallDb;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void beforeTest() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        loginPage.enterLoginDetails();
    }

    @AfterClass
    public static void afterTest(){

    }

    @Test
    public void pageElementsExist() {
        assertThat(homePage.hasTitle(), is(true));
        assertThat(homePage.hasNavbar(), is(true));
        assertThat(homePage.hasPeople(), is(true));
        assertThat(homePage.peopleHaveImages(), is(true));
    }
}
