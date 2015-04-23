package uk.co.o2.facewall.functionaltests.selenium.journeys;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.o2.facewall.functionaltests.selenium.common.SeleniumBase;
import uk.co.o2.facewall.functionaltests.selenium.pages.HomePage;
import uk.co.o2.facewall.functionaltests.selenium.pages.LoginPage;
import uk.co.o2.facewall.functionaltests.selenium.pages.RegisterPage;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RegistrationTest extends SeleniumBase {

    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private Random rand = new Random();
    private static final String NAME = "George Weasley";
    private static final String EMPTY_NAME ="";
    private static final String IMGURL = "http://theweasleys.com/george.jpg";
    private static final String INVALID_IMGURL = "notaurl";
    private String EMAIL = "";
    private static final String PASSWORD = "password";
    private static final String EXISTING_EMAIL = "doge@veryemail.com";
    private static final String INVALID_EMAIL = "notanemail.com";
    private static final String TEAM = "Ecom ARS";
    private static final String SCRUM = "weasleys";
    private static final String ROLE = "Developer";
    private static final String DETAILS = "I really like cats and work with kittens";
    private static final String LOCATION = "Bath Road";
    private static final String OFFICELOCATION = "1EB";

    @Before
    public void beforeTest(){
        homePage = new HomePage();
        homePage.navigateToHomePage();
        loginPage = new LoginPage();
        registerPage = loginPage.clickRegistrationTab();
    }

    @Test
    public void form_has_input_for_teams_when_no_team_in_db() {
        assertThat(registerPage.getInputTag("team"), is("input"));
    }

    @Test
    public void form_has_dropdown_for_teams_when_teams_in_db() {
        assertThat(registerPage.getInputTag("team"), is("select"));
    }

    @Test
    public void can_complete_register_user_journey() {
        EMAIL = "george" + rand.nextInt(50) + "@theweasleys.com";
        //Fill in form
        registerPage.enterFieldInForm("name", NAME);
        registerPage.enterFieldInForm("imgUrl", IMGURL);
        registerPage.enterFieldInForm("email", EMAIL);
        registerPage.enterFieldInForm("password", PASSWORD);
        registerPage.selectDropdown("team", TEAM);
        registerPage.enterFieldInForm("scrum", SCRUM);
        registerPage.selectDropdown("role", ROLE);
        registerPage.enterFieldInForm("details", DETAILS);
        registerPage.selectDropdown("location", LOCATION);
        registerPage.enterFieldInForm("officeLocation", OFFICELOCATION);
        registerPage.clickSubmit();

        //Check all details submitted are returned
        assertThat(registerPage.getSummaryItem("name"), is(NAME));
        assertThat(registerPage.getSummaryItem("imgUrl"), is(IMGURL));
        assertThat(registerPage.getSummaryItem("email"), is(EMAIL));
        assertThat(registerPage.getSummaryItem("role"), is(ROLE));
        assertThat(registerPage.getSummaryItem("location"), is(LOCATION));
        assertThat(registerPage.getSummaryItem("officeLocation"), is(OFFICELOCATION));
        assertThat(registerPage.getSummaryItem("scrum"), is(SCRUM));
        assertThat(registerPage.getSummaryItem("team"), is(TEAM));
        assertThat(registerPage.getSummaryItem("details"), is(DETAILS));

        //Go to overview to check person is showing
        homePage.navigateToHomePage();
        assertThat(homePage.personExists(NAME, TEAM, IMGURL), is(true));
    }

    @Test
    public void form_rejects_existing_email() {
        //Fill in form
        registerPage.enterFieldInForm("name", NAME);
        registerPage.enterFieldInForm("imgUrl", IMGURL);
        registerPage.enterFieldInForm("email", EXISTING_EMAIL);
        registerPage.enterFieldInForm("password", PASSWORD);
        registerPage.selectDropdown("team", TEAM);
        registerPage.enterFieldInForm("scrum", SCRUM);
        registerPage.selectDropdown("role", ROLE);
        registerPage.selectDropdown("location", LOCATION);
        registerPage.enterFieldInForm("officeLocation", OFFICELOCATION);
        registerPage.enterFieldInForm("details", DETAILS);
        registerPage.clickSubmit();

        //Assert that user has encountered an error is is still on registration page
        assertThat(registerPage.getTitle(), is("Facewall | Input your details"));
        assertThat(registerPage.getExistingEmailError(), is(EXISTING_EMAIL + " has already been used. Please use a different one."));
    }

    @Test
    public void form_rejects_invalid_email_field() {
        //Fill in form
        registerPage.enterFieldInForm("name", NAME);
        registerPage.enterFieldInForm("imgUrl", IMGURL);
        registerPage.enterFieldInForm("email", INVALID_EMAIL);
        registerPage.enterFieldInForm("password", PASSWORD);
        registerPage.selectDropdown("team", TEAM);
        registerPage.enterFieldInForm("scrum", SCRUM);
        registerPage.selectDropdown("role", ROLE);
        registerPage.selectDropdown("location", LOCATION);
        registerPage.enterFieldInForm("officeLocation", OFFICELOCATION);
        registerPage.enterFieldInForm("details", DETAILS);
        registerPage.clickSubmit();

        assertThat(registerPage.onRegistrationPage(), is(true));

        //Go to overview to check person is not showing
        homePage.navigateToHomePage();
        assertThat(homePage.personExists(NAME, TEAM, INVALID_EMAIL), is(false));
        //TODO Check for presence of error messages on page
    }

    @Test
    public void form_rejects_invalid_url_field() {
        EMAIL = "ben" + rand.nextInt(50) + "@theweasleys.com";
        //Fill in form
        registerPage.enterFieldInForm("name", NAME);
        registerPage.enterFieldInForm("imgUrl", INVALID_IMGURL);
        registerPage.enterFieldInForm("email", EMAIL);
        registerPage.enterFieldInForm("password", PASSWORD);
        registerPage.selectDropdown("team", TEAM);
        registerPage.enterFieldInForm("scrum", SCRUM);
        registerPage.selectDropdown("role", ROLE);
        registerPage.selectDropdown("location", LOCATION);
        registerPage.enterFieldInForm("officeLocation", OFFICELOCATION);
        registerPage.enterFieldInForm("details", DETAILS);
        registerPage.clickSubmit();

        assertThat(registerPage.onRegistrationPage(), is(true));

        //Go to overview to check person is not showing
        homePage.navigateToHomePage();
        assertThat(homePage.personExists(NAME, TEAM, INVALID_IMGURL), is(false));
        //TODO Check for presence of error messages on page
    }

    @Test
    public void for_rejects_empty_name_field() {
        EMAIL = "jeff" + rand.nextInt(50) + "@theweasleys.com";
        //Fill in form
        registerPage.enterFieldInForm("name", EMPTY_NAME);
        registerPage.enterFieldInForm("imgUrl", IMGURL);
        registerPage.enterFieldInForm("email", EMAIL);
        registerPage.enterFieldInForm("password", PASSWORD);
        registerPage.selectDropdown("team", TEAM);
        registerPage.enterFieldInForm("scrum", SCRUM);
        registerPage.selectDropdown("role", ROLE);
        registerPage.selectDropdown("location", LOCATION);
        registerPage.enterFieldInForm("officeLocation", OFFICELOCATION);
        registerPage.enterFieldInForm("details", DETAILS);
        registerPage.clickSubmit();

        assertThat(registerPage.onRegistrationPage(), is(true));

        //Go to overview to check person is not showing
        homePage.navigateToHomePage();
        assertThat(homePage.personExists(NAME, TEAM, INVALID_IMGURL), is(false));
        //TODO Check for presence of error messages on page
    }

}
