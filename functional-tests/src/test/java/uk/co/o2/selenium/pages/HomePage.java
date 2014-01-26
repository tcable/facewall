package uk.co.o2.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.co.o2.selenium.common.Configuration;
import uk.co.o2.selenium.common.WebBrowser;

import java.util.List;

public class HomePage {

    public void navigateToHomePage() {
        WebBrowser.navigateTo(Configuration.baseUrl);
    }

    public RegisterPage clickRegistrationTab() {
        WebBrowser.findElement(By.id("register")).click();
        return new RegisterPage();
    }

    public SearchPage clickSearchTab() {
        WebBrowser.findElement(By.id("search")).click();
        return new SearchPage();
    }

    public boolean personExists(String name, String team, String imgUrl) {
        List<WebElement> elements = WebBrowser.findElements(By.className("entry"));

        for (WebElement element: elements) {
            String currentName = element.findElement(By.className("entryName")).getText();
            String currentTeam = element.findElement(By.className("teamName")).getText();
            String currentImgUrl  = element.findElement(By.cssSelector("img")).getAttribute("src");
            if(currentName.equals(name) && currentTeam.equals(team) && currentImgUrl.equals(imgUrl)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasTitle() {
        if (WebBrowser.findElement(By.id("homeTitle")).isDisplayed()) {
            return true;

        } else return false;
    }

    public Boolean hasNavbar() {
        if (WebBrowser.findElement(By.className("nav")).isDisplayed()){
            return  true;
        } else return false;
    }

    public Boolean hasPeople() {
        if (WebBrowser.findElement(By.className("entry")).isDisplayed())   {
            return true;
        } else return false;
    }

    public Boolean hasFooter() {
        if (WebBrowser.findElement(By.className("footer")).isDisplayed()){
            return true;
        } else return false;
    }
}
