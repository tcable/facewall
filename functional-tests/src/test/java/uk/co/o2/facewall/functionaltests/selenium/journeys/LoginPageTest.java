package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Before;
import org.junit.Test;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginPageTest extends SeleniumBase {

    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void beforeTest() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void checkLoginPageAcceptsValidEmailAndPassword() {
        // TODO password needs to be implemented
        loginPage.enterLoginDetails();

        assertThat(homePage.hasTitle(), is(true));
    }

    @Test
    public void checkLoginPageBlocksInvalidEmailAndPassword() {
        // TODO password needs to be implemented
        loginPage.enterInvalidLoginDetails();

        assertThat(loginPage.hasLoginSubmit(), is(true));
    }
}
