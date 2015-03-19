package uk.co.o2.facewall.functionaltests.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.co.o2.facewall.functionaltests.selenium.common.WebBrowser;

import java.util.List;

public class TeamDetailsPage {


    public Boolean isOnPageForTeam(String teamName) {
        WebElement Name = WebBrowser.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/h1"));
        // TODO: no xpath
        return Name.getText().equals(teamName);
    }

    public Boolean personIsInTeam(String personName) {
        List<WebElement> personArray = WebBrowser.findElements(By.className("entry"));

        boolean result = false;

        for(int i = 0; i < personArray.size(); i++) {
            if(personArray.get(i).getAttribute("data-qa-person-name").contains(personName)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
