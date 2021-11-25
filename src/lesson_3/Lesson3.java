package lesson_3;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Lesson3 {

    public AppiumDriver<WebElement> driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","avd1");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/org.wikipedia.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testElementHasText()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Search…",
                "This element has other text!");
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java");
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"));
        List<WebElement> elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_container"));
        Assert.assertTrue("Search result count <=1 !", elements.size() > 1);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"));
        waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"));
    }

    @Test
    public void testSearchResultsContainsText()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java");
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"));
        List<WebElement> elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (WebElement element : elements){
            boolean isContains = element.getText().contains("Java");
            Assert.assertTrue("Java is not found in search result!",isContains);
        }
    }

    public WebElement waitForElementPresent(By by, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find element " + by + "!\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by)
    {
        return waitForElementPresent(by, 15);
    }

    public WebElement waitForElementAndClick(By by, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    public WebElement waitForElementAndClick(By by)
    {
        return waitForElementAndClick(by, 15);
    }

    public WebElement waitForElementAndSendKeys(By by, String value)
    {
        return waitForElementAndSendKeys(by, value, 15);
    }

    public boolean waitForElementNotPresent(By by, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Element "+ by + " is still present!");
        return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(By by)
    {
        return  waitForElementNotPresent(by, 15);
    }

    public WebElement waitForElementAndClear(By by, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.clear();
        return element;
    }
    public WebElement waitForElementAndClear(By by)
    {
        WebElement element = waitForElementPresent(by, 15);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String expected, String error_message)
    {
        WebElement element = waitForElementPresent(by);
        String actual = element.getText();
        Assert.assertEquals(error_message,expected, actual);
    }
    public void assertElementHasText(By by, String expected)
    {
        WebElement element = waitForElementPresent(by);
        String actual = element.getText();
        Assert.assertEquals("This element has other text!",expected, actual);
    }

}
