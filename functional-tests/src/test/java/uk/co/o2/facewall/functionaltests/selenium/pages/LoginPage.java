package uk.co.o2.facewall.functionaltests.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.co.o2.facewall.functionaltests.selenium.common.Configuration;
import uk.co.o2.facewall.functionaltests.selenium.common.WebBrowser;

import java.util.List;

public class LoginPage {

    public HomePage enterLoginDetails() {
        WebBrowser.findElement(By.name("email")).sendKeys("person@email.com");
        WebBrowser.findElement(By.id("login")).click();
        return new HomePage();

    }
}
