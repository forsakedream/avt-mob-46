package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public static final String
            FOOTER = "xpath://*[@text='View page in browser']",
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            OPTIONS = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']//*[@content-desc='More options']",
            ADD_TO_LIST = "xpath://*[contains(@text, 'Add to reading list')]",
            ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
            LIST_TITLE_FIELD = "id:org.wikipedia:id/text_input",
            OK = "xpath://*[@text='OK']",
            LIST_TITLE = "id:org.wikipedia:id/item_title",
            CLOSE_BUTTON = "xpath://*[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }

    public void swipeUpToFooter(){
        this.swipeUpToFindElements(FOOTER);
    }

    public void waitForArticleTitle(){
        this.waitForElementPresent(TITLE);
    }

    public void addArticleToNewList(String list){
        this.waitForElementAndClick(OPTIONS);
        this.waitForElementAndClick(ADD_TO_LIST);
        this.waitForElementAndClick(ONBOARDING_BUTTON);
        this.waitForElementAndClear(LIST_TITLE_FIELD);
        this.waitForElementAndSendKeys(LIST_TITLE_FIELD, list);
        this.waitForElementAndClick(OK);
    }

    public void addArticleToExistingList(String list){
        this.waitForElementAndClick(OPTIONS);
        this.waitForElementAndClick(ADD_TO_LIST);
        this.waitForElementContainsTextAndCLick(LIST_TITLE, list);
    }

    public void closeArticle(){
        this.waitForElementAndClick(CLOSE_BUTTON);
    }

    public void assertArticleHasTitle(String title)
    {
        waitForElementPresent(TITLE);
        assertElementHasText(TITLE, title);
    }

}
