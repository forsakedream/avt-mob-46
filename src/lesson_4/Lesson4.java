package lesson_4;

import io.appium.java_client.TouchAction;
import lesson_3.Lesson3;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Timer;

public class Lesson4 extends Lesson3 {
    @Test
    public void testswipeArticle()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium");
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Appium']"));
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
        swipeUpToFindElements(By.xpath("//*[@text='View page in browser']"));

    }

    @Test
    public void saveTwoArticlesToMyList()
    {
        searchAndSelectArticle("Appium");
        addArticleToNewList("Learning");
        closeArticle();
        searchAndSelectArticle("Java", "Object-oriented programming language");
        addArticleToExistingList("Learning");
        closeArticle();
        goToMyListsAndSelectList("Learning");
        deleteArticleFromList("Appium");
        assertElementHasText(By.id("org.wikipedia:id/page_list_item_title"), "Java (programming language)");
        waitForElementAndClick(By.id("org.wikipedia:id/page_list_item_title"));
        assertElementHasText(By.id("org.wikipedia:id/view_page_title_text"), "Java (programming language)");
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

    public void searchAndSelectArticle(String title, String description){
        search(title);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='" + description + "']"));
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
    }

    public void searchAndSelectArticle(String title){
        search(title);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + title + "']"));
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
    }

    public void search(String value){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"));
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), value);
    }

    public void addArticleToNewList(String list){
        waitForElementAndClick(By.xpath("//*[@content-desc='More options']"));
        waitForElementAndClick(By.xpath("//*[contains(@bounds, '[480,372][1068,516]')]"));
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"));
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"));
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), list);
        waitForElementAndClick(By.xpath("//*[@text='OK']"));
    }

    public void addArticleToExistingList(String list){
        waitForElementAndClick(By.xpath("//*[@content-desc='More options']"));
        waitForElementAndClick(By.xpath("//*[contains(@bounds, '[480,372][1068,516]')]"));
        waitForElementContainsTextAndCLick(By.id("org.wikipedia:id/item_title"), list);
    }

    public void closeArticle(){
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"));
    }

    public void goToMyLists(){
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"));
    }

    public void goToMyListsAndSelectList(String list){
        goToMyLists();
        waitForElementContainsTextAndCLick(By.id("org.wikipedia:id/item_title"), list);
    }

    public void deleteArticleFromList(String article){
        String articleLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='"+ article + "']";
        swipeElementToLeft(By.xpath(articleLocator));
        waitForElementNotPresent(By.xpath(articleLocator));
    }

}
