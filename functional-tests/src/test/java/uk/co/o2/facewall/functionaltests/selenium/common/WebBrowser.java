package uk.co.o2.facewall.functionaltests.selenium.common;

import com.google.common.base.Function;
import com.thoughtworks.selenium.DefaultSelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WebBrowser {

    private static WebDriver driver = null;
    public static DefaultSelenium selenium = null;
    public static String browserName = "";

    public WebBrowser() {
    }

    public static WebDriver start(String browser, String browserVersion) {

        DesiredCapabilities capabillities = null;

        System.out.println("Setting up WebDriver using: " + browser + " with version " + browserVersion);
        driver = null;
        browserName = browser;

        if (browser.equalsIgnoreCase("firefox")) {
            capabillities = DesiredCapabilities.firefox();
            capabillities.setCapability("version", browserVersion);
            capabillities.setCapability("platform", Platform.LINUX);
            capabillities.setJavascriptEnabled(true);
            driver = new FirefoxDriver(capabillities);
        }
        //TODO Implement chrome driver
//        else if (browser.equalsIgnoreCase("chrome")) {
//            capabillities = DesiredCapabilities.chrome();
//            capabillities.setCapability("platform", Platform.XP);
//            capabillities.setJavascriptEnabled(true);
//        }
        //TODO Look into browser timeouts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeBrowser() {
        driver.close();
    }

    public static void deleteCookieNamed(String named) {
        driver.manage().deleteCookieNamed(named);
    }

    public static Set<Cookie> getCookies() {
        return driver.manage().getCookies();
    }

    public static JavascriptExecutor getJavascriptExecutor(){
        return (JavascriptExecutor)driver;
    }

    public static void deleteAllCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (final Exception e) {
            System.out.println("Could not delete cookies " + e);
        }
    }

    public static void navigateTo(final String url) {
        driver.get(url);
    }

    public static WebElement findElement(final By by) {
        return driver.findElement(by);
    }

    public static WebElement findElementWithFluentWait(final By by) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(6000, MILLISECONDS)
                .pollingEvery(250, MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    };

    public static boolean elementExists(final By by) {
        try {
            findElementWithFluentWait(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUrl() {
        return driver.getCurrentUrl();
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static void refresh(){
        driver.navigate().refresh();
    }

    public static String getPageSource() {
        return driver.getPageSource();
    }

    public static List<WebElement> findElements(final By by) {
        return driver.findElements(by);
    }

    public static void browserBack() {
        driver.navigate().back();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
