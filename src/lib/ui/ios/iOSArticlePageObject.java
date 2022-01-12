package lib.ui.ios;

import static io.appium.java_client.ios.touch.IOSPressOptions.iosPressOptions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSTouchAction;
import static java.time.Duration.ofMillis;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class iOSArticlePageObject extends ArticlePageObject {

    private static final String MY_LISTS = "chain:**/XCUIElementTypeButton[`label == \"Save for later\"`]",
    CREATE_LIST = "chain:**/XCUIElementTypeStaticText[`label == \"Create a new list\"`]",
    CREATE_READING_LIST = "chain:**/XCUIElementTypeButton[`label == \"Create reading list\"`]";

    static {
        FOOTER = "xpath://XCUIElementTypeStaticText[@name=\"View article in browser\" and @visible=\"true\"]";
        TITLE = "xpath://XCUIElementTypeStaticText[@height=\"32\"]";
        LIST_TITLE_FIELD = "chain:**/XCUIElementTypeTextField[`value == \"reading list title\"`]";
        LIST_TITLE = "chain:**/XCUIElementTypeStaticText[`label CONTAINS \"{LIST_TITLE}\"`]";
        CLOSE_BUTTON = "chain:**/XCUIElementTypeButton[`label == \"W\"`]";
    }

    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void longTap(WebElement element) {
        AppiumDriver<WebElement> driver = (AppiumDriver) this.driver;
        new IOSTouchAction(driver)
                .press(iosPressOptions()
                        .withElement(element(element))
                        .withPressure(1))
                .waitAction(waitOptions(ofMillis(2000)))
                .release()
                .perform();
    }

    @Override
    public void addArticleToNewList(String list_title) {
        WebElement my_lists_element = waitForElementPresent(MY_LISTS);
        this.longTap(my_lists_element);
        waitForElementAndClick(CREATE_LIST);
        waitForElementAndSendKeys(LIST_TITLE_FIELD, list_title);
        waitForElementAndClick(CREATE_READING_LIST);
    }

    @Override
    public void addArticleToExistingList(String list_title) {
        WebElement my_lists_element = waitForElementPresent(MY_LISTS);
        this.longTap(my_lists_element);
        String EXISTING_LIST = LIST_TITLE.replace("{LIST_TITLE}", list_title);
        waitForElementAndClick(EXISTING_LIST);
    }

}
