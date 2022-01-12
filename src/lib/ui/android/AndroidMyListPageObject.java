package lib.ui.android;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        LIST_TITLE = "id:org.wikipedia:id/item_title";
        ARTICLE_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']";
        ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void deleteArticleFromList(String article)
    {
        String articleLocator = getArticleElementByTitle(article);
        this.swipeElementToLeft(articleLocator);
        this.waitForElementNotPresent(articleLocator);
    }
}
