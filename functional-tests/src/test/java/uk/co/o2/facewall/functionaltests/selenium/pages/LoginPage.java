package uk.co.o2.facewall.functionaltests.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.co.o2.facewall.functionaltests.selenium.common.Configuration;
import uk.co.o2.facewall.functionaltests.selenium.common.WebBrowser;

import java.util.List;

public class LoginPage {

    public HomePage enterLoginDetails() {
        WebBrowser.findElement(By.name("email")).sendKeys("fahren@veryemail.com");
        //WebBrowser.findElement(By.name("password")).sendKeys("fahren123");
        // TODO: add password function
        WebBrowser.findElement(By.id("login")).click();
        return new HomePage();
    }

    public HomePage enterInvalidLoginDetails() {
        WebBrowser.findElement(By.name("email")).sendKeys("doge####@veryemail.com");
        // TODO: add password function
        WebBrowser.findElement(By.id("login")).click();
        return new HomePage();
    }

    public RegisterPage clickRegistrationTab() {
        WebBrowser.findElement(By.id("register")).click();
        return new RegisterPage();
    }

    public boolean hasLoginSubmit() {
        return WebBrowser.findElement(By.id("login")).isDisplayed();
    }

}
