package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Before;
import org.junit.Test;
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
    private static final String TEAM = "Ecom Ars";
    private HomePage homePage;
    private LoginPage loginPage;
    private PersonDetailsPage personDetailsPage;

    @Before
    public void beforeTest() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        homePage =  loginPage.enterLoginDetails();
    }

    @Test
    public void person_has_a_name() {
        personDetailsPage = homePage.clickPerson(PERSON_NAME);

        assertThat(personDetailsPage.getPersonName(), is(PERSON_NAME));
    }

    @Test
    public void person_has_an_img_tag() {
        personDetailsPage = homePage.clickPerson(PERSON_NAME);

        assertThat(personDetailsPage.imageExists(), is(true));
    }

    @Test
    public void person_has_an_email() {
        personDetailsPage = homePage.clickPerson(PERSON_NAME);

        assertThat(personDetailsPage.hasEmail(EMAIL), is(true));
    }

    @Test
    public void person_has_a_role() {
        personDetailsPage = homePage.clickPerson(PERSON_NAME);

        assertThat(personDetailsPage.hasRole(ROLE), is(true));
    }

    @Test
    public void person_has_a_team() {
        personDetailsPage = homePage.clickPerson(PERSON_NAME);

        assertThat(personDetailsPage.hasTeam(TEAM), is(true));
    }

}
