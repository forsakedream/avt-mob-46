package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListPageObject extends MainPageObject{

    public static final String
            LIST_TITLE = "org.wikipedia:id/item_title",
            ARTICLE_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']",
            ARTICLE_TITLE = "org.wikipedia:id/page_list_item_title";


    public MyListPageObject(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }

    /* TEMPLATES_METHODS */
    private static String getArticleElementByTitle(String substring)
    {
        return ARTICLE_TITLE_TPL.replace("{TITLE}", substring);
    }
    /* TEMPLATES_METHODS */


    public void selectList(String list)
    {
        this.waitForElementContainsTextAndCLick(By.id(LIST_TITLE), list);
    }

    public void deleteArticleFromList(String article)
    {
        String articleLocator = getArticleElementByTitle(article);
        this.swipeElementToLeft(By.xpath(articleLocator));
        this.waitForElementNotPresent(By.xpath(articleLocator));
    }

    public void assertListHasArticle(String article)
    {
        this.assertElementHasText(By.id(ARTICLE_TITLE), article);
    }

    public void clickArticleFromList(String article)
    {
        String articleLocator = getArticleElementByTitle(article);
        this.waitForElementAndClick(By.xpath(articleLocator));
    }
}
