package uk.co.o2.facewall.functionaltests.selenium.common;

public class Configuration {

    //TODO Actually use this for something.
    public static String testDriver = System.getProperty("qa.testdriver", "webdriver");
    //TODO Implement other browsers for testing
    public static String browser = System.getProperty("qa.browser", "firefox");
    public static String browserVersion = System.getProperty("qa.browserVersion", "31");
    //Used for checking whether running locally in tests - refactor out
    public static String runNeoDb = System.getProperty("where", "local");

    public static String baseUrl = System.getProperty("testUrl", "http://localhost:9000");
}
