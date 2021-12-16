package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String FOOTER, TITLE, OPTIONS, ADD_TO_LIST, ONBOARDING_BUTTON, LIST_TITLE_FIELD,
            OK, LIST_TITLE, CLOSE_BUTTON;

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
