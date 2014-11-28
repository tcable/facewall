package uk.co.o2.facewall.functionaltests.selenium.journeys;

import uk.co.o2.facewall.databaseutils.FacewallTestDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.SearchPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.SearchResultsPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.SinglePersonSearchResultsPage;

import static uk.co.o2.facewall.databaseutils.FacewallTestDatabaseFactory.createFacewallTestDatabaseWrappingExistingDatabase;
import static uk.co.o2.facewall.databaseutils.fixture.Fixtures.newFixtures;
import static uk.co.o2.facewall.databaseutils.fixture.PersonDataFactory.defaultPerson;
import static uk.co.o2.facewall.databaseutils.fixture.TeamDataFactory.defaultTeamWithDefaultMembers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.neo4j.rest.graphdb.GraphDatabaseFactory.databaseFor;

public class SearchTest extends SeleniumBase {

    private static GraphDatabaseService neoDb;
    private static FacewallTestDatabase facewallDb;
    private HomePage homePage;
    private SearchPage searchPage;
    private SinglePersonSearchResultsPage singlePersonSearchResultsPage;
    private SearchResultsPage searchResultsPage;

    @BeforeClass
    public static void beforeClass() {
        neoDb = databaseFor("http://localhost:7474/db/data/");
        facewallDb = createFacewallTestDatabaseWrappingExistingDatabase(neoDb);
        facewallDb.clear();
        facewallDb.initialise();
    }

    @Before
    public void beforeTest() {
        facewallDb.clear();
        facewallDb.initialise();
        homePage = new HomePage();
        homePage.navigateToHomePage();  //Initial landing on homepage
        searchPage = homePage.clickSearchTab(); //Now on search page
    }

    @After
    public void afterTest() throws InterruptedException {
        facewallDb.clear();
        facewallDb.initialise();
    }

    @Test
    public void search_for_team() throws Exception {
        facewallDb.seedFixtures(newFixtures().withTeams(defaultTeamWithDefaultMembers().withProperty("name", "Ecom Ars")));
        searchResultsPage = searchPage.searchTeam("Ecom Ars");
        assertThat(searchResultsPage.teamExists("Ecom Ars"), is(true));
    }

    @Test
     public void no_person_search_results() throws Exception {
        facewallDb.seedFixtures(newFixtures().withTeamlessPersons(defaultPerson().withProperty("name", "Fred Weasley")));
        singlePersonSearchResultsPage = searchPage.searchPerson("Norman Cook");
        assertThat(singlePersonSearchResultsPage.personExists("Norman Cook"), is(false));
    }

    @Test
    public void no_team_search_results() throws Exception {
        facewallDb.seedFixtures(newFixtures().withTeams(defaultTeamWithDefaultMembers().withProperty("name", "Ecom Ars")));
        searchResultsPage = searchPage.searchTeam("Team Unknown");
        assertThat(searchResultsPage.teamExists("Team Unknown"), is(false));
    }

    @Test
    public void no_team_and_no_person_search_results_displays_message() throws Exception {
        searchResultsPage = searchPage.searchTeam("No results");
        assertThat(searchResultsPage.hasNoResultsMessage(), is(true));
    }

}
