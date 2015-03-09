package uk.co.o2.facewall.functionaltests.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.co.o2.facewall.functionaltests.selenium.common.WebBrowser;

import java.util.List;

public class TeamListPage {

    public TeamDetailsPage clickOPPTeam() {
        WebBrowser.findElement(By.linkText("OPP")).click();
        return new TeamDetailsPage();
    }

    public boolean findAndMatchTeam(String teamName) {
        List<WebElement> teamArray = WebBrowser.findElements(By.className("entry"));

        boolean result = false;

        for(int i = 0; i < teamArray.size(); i++) {
            if(teamArray.get(i).getAttribute("data-qa-team-name").contains(teamName)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
