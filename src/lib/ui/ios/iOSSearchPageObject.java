package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "chain:**/XCUIElementTypeSearchField[`label == \"Search Wikipedia\"`]";
        SEARCH_INPUT = "chain:**/XCUIElementTypeSearchField[`label == \"Search Wikipedia\"`]";
        SEARCH_RESULT_BY_TITLE_TPL = "chain:**/XCUIElementTypeStaticText[`label CONTAINS \"{TITLE}\"`]";
        SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[contains(@name,'{TITLE}')]/following-sibling::*[contains(@name, '{DESCRIPTION}')]";
        SEARCH_RESULTS_CONTAINER = "chain:**/XCUIElementTypeCell";
        SEARCH_RESULT = "chain:**/XCUIElementTypeStaticText";
        SEARCH_CANCEL_BUTTON = "chain:**/XCUIElementTypeButton[`label == \"Clear text\"`]";
    }

    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
