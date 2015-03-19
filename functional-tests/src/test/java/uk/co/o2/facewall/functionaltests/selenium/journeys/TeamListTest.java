package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Test;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.TeamListPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeamListTest extends SeleniumBase {

    @Test
    public void teams_on_page() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        LoginPage loginPage = new LoginPage();
        homePage =  loginPage.enterLoginDetails();
        TeamListPage teamListPage = homePage.clickTeamTab();

        assertThat(teamListPage.isOnPageForTeam("OPP"), is(true));
    }
}
