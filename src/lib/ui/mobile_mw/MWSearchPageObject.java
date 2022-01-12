package lib.ui.mobile_mw;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://a[contains(@data-title, '{TITLE}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://a[contains(@data-title, '{TITLE}')]/*[contains(text(), '{DESCRIPTION}')]";
        SEARCH_RESULTS_CONTAINER = "css:results-list-container";
        SEARCH_RESULT = "xpath://a[contains(@class, 'title')]";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
