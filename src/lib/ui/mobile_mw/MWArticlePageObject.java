package lib.ui.mobile_mw;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    private static final String REMOVE_FROM_LIST = "css:a.watched";

    static {
        FOOTER = "css:li#footer-places-desktop-toggle";
        TITLE = "css:h1#firstHeading";
        ADD_TO_LIST = "xpath://*[contains(@class, 'watch-this-article')][contains(text(), 'Watch')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void removeFromList() {
        waitForElementAndClick(REMOVE_FROM_LIST);
        waitForElementPresent(ADD_TO_LIST);
    }

    public void addToList() {
        waitForElementPresent("css:a#ca-watch");
        if (isElementPresent(REMOVE_FROM_LIST)) {
            removeFromList();
        }
        waitForElementAndClick(ADD_TO_LIST);
    }

    @Override
    public void addArticleToNewList(String list_title) {

    }

    @Override
    public void addArticleToExistingList(String list_title) {

    }

}
