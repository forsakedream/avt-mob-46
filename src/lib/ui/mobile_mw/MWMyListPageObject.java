package lib.ui.mobile_mw;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    private static final String REMOVE_BUTTON_TPL = "xpath://*[contains(text(), '{TITLE}')]/following::*[contains(@class, 'watch-this-article')]",
                                ADD_BUTTON="xpath://*[contains(@class, 'watch-this-article')][contains(text(), 'Watch')]";

    static {
        LIST_TITLE = "css:h1#firstHeading";
        ARTICLE_TITLE_TPL = "xpath://*[contains(text(), '{TITLE}')]";
        ARTICLE_TITLE = "css:li.page-summary.with-watchstar";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void deleteArticleFromList(String article) {
        String REMOVE_BUTTON = REMOVE_BUTTON_TPL.replace("{TITLE}", article);
        waitForElementAndClick(REMOVE_BUTTON);
        waitForElementPresent(ADD_BUTTON);
        driver.navigate().refresh();
    }
}
