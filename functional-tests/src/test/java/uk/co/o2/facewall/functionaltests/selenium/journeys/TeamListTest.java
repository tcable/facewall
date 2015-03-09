package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Test;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.TeamListPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeamListTest extends SeleniumBase {

    private HomePage homePage;
    private LoginPage loginPage;
    private TeamListPage teamListPage;

    @Test
    public void teams_on_page() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage =  loginPage.enterLoginDetails();  // initial landing on homepage
        teamListPage = homePage.clickTeamTab(); //click team list tab

        assertThat(teamListPage.findAndMatchTeam("OPP"), is(true));
    }
}
