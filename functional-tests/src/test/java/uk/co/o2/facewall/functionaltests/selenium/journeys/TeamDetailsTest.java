package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Test;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.TeamDetailsPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.TeamListPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by tom on 06/03/15.
 */
public class TeamDetailsTest extends SeleniumBase{

    private HomePage homePage;
    private LoginPage loginPage;
    private TeamListPage teamListPage;
    private TeamDetailsPage teamDetailsPage;

    @Test
    public void team_details() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage =  loginPage.enterLoginDetails();  // initial landing on homepage
        teamListPage = homePage.clickTeamTab(); //click team list tab
        teamDetailsPage = teamListPage.clickOPPTeam(); //click through to team details page

        assertThat(teamDetailsPage.findAndMatchName("OPP"), is(true));
        assertThat(teamDetailsPage.findAndMatchPerson("Stuart Gray"), is(true));
    }
}
