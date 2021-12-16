package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.WebElement;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        FOOTER = "xpath://*[@text='View page in browser']";
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        OPTIONS = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']//*[@content-desc='More options']";
        ADD_TO_LIST = "xpath://*[contains(@text, 'Add to reading list')]";
        ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
        LIST_TITLE_FIELD = "id:org.wikipedia:id/text_input";
        OK = "xpath://*[@text='OK']";
        LIST_TITLE = "id:org.wikipedia:id/item_title";
        CLOSE_BUTTON = "xpath://*[@content-desc='Navigate up']";
    }
    public AndroidArticlePageObject(AppiumDriver<WebElement> driver) {
        super(driver);
    }
}
