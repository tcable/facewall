package uk.co.o2.facewall.functionaltests.selenium.pages;

import org.openqa.selenium.By;
import uk.co.o2.facewall.functionaltests.selenium.common.WebBrowser;

public class SearchPage {

    public SearchResultsPage searchPerson(String personName) throws InterruptedException {
        WebBrowser.findElement(By.name("keywords")).sendKeys(personName);
        return new SearchResultsPage();
    }

    public SearchResultsPage searchTeam(String teamName) throws InterruptedException {
        WebBrowser.findElement(By.name("keywords")).sendKeys(teamName);
        return new SearchResultsPage();
    }
}
