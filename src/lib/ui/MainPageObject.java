package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver<WebElement> driver;

    public MainPageObject(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
    }

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    public void swipeElementToLeft(By by)
    {
        WebElement element = waitForElementPresent(by);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int midle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, midle_y).waitAction(200).moveTo(left_x, midle_y).release().perform();

    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElements(By by)
    {
        driver.findElements(by);
        int max_swipes = 20;
        int already_swiped = 0;
        while (driver.findElements(by). size() == 0){
            if (already_swiped > max_swipes){
                waitForElementPresent(by);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void waitForElementContainsTextAndCLick(By by, String value, long timeoutInSeconds)
    {
        waitForElementPresent(by, timeoutInSeconds);
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements){
            if (element.getText().contains(value)) {
                element.click();
                return;
            }
        }
    }

    public void waitForElementContainsTextAndCLick(By by, String value)
    {
        waitForElementContainsTextAndCLick(by, value, 15);
    }

    public void assertElementPresent(By by){
        Assert.assertEquals("This page doesn't contains element " + by + " !",
                1, driver.findElements(by).size());
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
