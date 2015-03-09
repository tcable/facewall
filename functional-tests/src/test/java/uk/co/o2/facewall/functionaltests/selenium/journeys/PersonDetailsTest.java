package uk.co.o2.facewall.functionaltests.selenium.journeys;

import uk.co.o2.facewall.databaseutils.FacewallTestDatabase;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import uk.co.o2.facewall.functionaltests.selenium.common.Configuration;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.PersonDetailsPage;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonDetailsTest extends SeleniumBase {

    private static final String PERSON_NAME = "Fahran Wallace";
    private static final String EMAIL = "fahren@veryemail.com";
    private static final String ROLE = "Developer";
    private HomePage homePage;
    private LoginPage loginPage;
    private PersonDetailsPage personDetailsPage;

    @Test
    public void person_has_a_name() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage =  loginPage.enterLoginDetails();  // initial landing on homepage
        personDetailsPage = homePage.clickPerson(PERSON_NAME); //click through to person details page

        assertThat(personDetailsPage.getPersonName(), is(PERSON_NAME));
    }

    @Test
    public void person_has_an_img_tag() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage = loginPage.enterLoginDetails(); // initial landing on homepage
        personDetailsPage = homePage.clickPerson(PERSON_NAME); //click through to person details page
            assertThat(personDetailsPage.imageExists(), is(true));
    }

    @Test
    public void person_has_an_email() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage = loginPage.enterLoginDetails();  // initial landing on homepage
        personDetailsPage = homePage.clickPerson(PERSON_NAME); //click through to person details page

        assertThat(personDetailsPage.hasEmail(EMAIL), is(true));
    }

    @Test
    public void person_has_a_role() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage = loginPage.enterLoginDetails();  // initial landing on homepage
        personDetailsPage = homePage.clickPerson(PERSON_NAME); //click through to person details page

        assertThat(personDetailsPage.hasRole(ROLE), is(true));
    }

}
