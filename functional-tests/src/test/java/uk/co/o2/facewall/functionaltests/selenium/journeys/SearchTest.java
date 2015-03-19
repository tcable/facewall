package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.SearchPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.SearchResultsPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SearchTest extends SeleniumBase {

    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    @Before
    public void beforeTest() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        LoginPage loginPage = new LoginPage();
        loginPage.enterLoginDetails();
        searchPage = homePage.clickSearchTab();
    }

    //TODO Reinstate these tests
    @Ignore
    @Test
    public void search_for_person() throws Exception {
        searchResultsPage = searchPage.searchPerson("Fred Weasley");
//        assertThat(searchResultsPage.getPersonName(), is("Fred Weasley"));
    }

    //TODO Reinstate these tests
    @Ignore
    @Test
    public void search_for_person_with_mixed_casing() throws Exception {
        searchResultsPage = searchPage.searchPerson("fReD JiMMY WeasLeY");
//        assertThat(searchResultsPage.getPersonName(), is("Fred Jimmy Weasley"));
    }

    @Test
    public void search_for_team() throws Exception {
        searchResultsPage = searchPage.searchTeam("Ecom Ars");
        assertThat(searchResultsPage.teamExists("Ecom Ars"), is(true));
    }

    //TODO Reinstate these tests
    @Ignore
    @Test
     public void no_person_search_results() throws Exception {
        searchResultsPage = searchPage.searchPerson("Norman Cook");
//        assertThat(searchResultsPage.personExists("Norman Cook"), is(false));
    }

    @Test
    public void no_team_search_results() throws Exception {
        searchResultsPage = searchPage.searchTeam("Team Unknown");
        assertThat(searchResultsPage.teamExists("Team Unknown"), is(false));
    }

    @Test
    public void no_team_and_no_person_search_results_displays_message() throws Exception {
        searchResultsPage = searchPage.searchTeam("No results");
        assertThat(searchResultsPage.hasNoResultsMessage(), is(true));
    }

    //TODO Write test that selects person and checks page
}
