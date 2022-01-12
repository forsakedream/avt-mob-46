package lib.ui.ios;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListPageObject {
    private static final String
            EDIT = "chain:**/XCUIElementTypeButton[`label == \"Edit\"`]",
            REMOVE = "chain:**/XCUIElementTypeStaticText[`label == \"Remove\"`]",
            SELECTOR = "chain:**/XCUIElementTypeImage[`name == \"unselected\"`][2]",
            SELECTED = "chain:**/XCUIElementTypeImage[`name == \"selected\"`]";

    static {
        LIST_TITLE = "chain:**/XCUIElementTypeStaticText";
        ARTICLE_TITLE_TPL = "chain:**/XCUIElementTypeStaticText[`label == \"{TITLE}\"`]";
        ARTICLE_TITLE = "chain:**/XCUIElementTypeStaticText[1]";
    }

    public iOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void deleteArticleFromList(String article) {
        waitForElementAndClick(EDIT);
        waitForElementAndClick(SELECTOR);
        waitForElementPresent(SELECTED);
        waitForElementAndClick(REMOVE);
    }
}
