package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public static final String
            FOOTER = "//*[@text='View page in browser']",
            TITLE = "org.wikipedia:id/view_page_title_text",
            OPTIONS = "//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@content-desc='More options']",
            ADD_TO_LIST = "//*[contains(@bounds, '[480,372][1068,516]')]",
            ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
            LIST_TITLE_FIELD = "org.wikipedia:id/text_input",
            OK = "//*[@text='OK']",
            LIST_TITLE = "org.wikipedia:id/item_title",
            CLOSE_BUTTON = "//*[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }

    public void swipeUpToFooter(){
        this.swipeUpToFindElements(By.xpath(FOOTER));
    }

    public void waitForArticleTitle(){
        this.waitForElementPresent(By.id(TITLE));
    }

    public void addArticleToNewList(String list){
        this.waitForElementAndClick(By.xpath(OPTIONS));
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST));
        this.waitForElementAndClick(By.id(ONBOARDING_BUTTON));
        this.waitForElementAndClear(By.id(LIST_TITLE_FIELD));
        this.waitForElementAndSendKeys(By.id(LIST_TITLE_FIELD), list);
        this.waitForElementAndClick(By.xpath(OK));
    }

    public void addArticleToExistingList(String list){
        this.waitForElementAndClick(By.xpath(OPTIONS));
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST));
        this.waitForElementContainsTextAndCLick(By.id(LIST_TITLE), list);
    }

    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(CLOSE_BUTTON));
    }

    public void assertArticleHasTitle(String title)
    {
        WebElement element = waitForElementPresent(By.id(TITLE));
        assertElementHasText(By.id(TITLE), title);
    }

}
