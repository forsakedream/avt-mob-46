package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{
    public static final String SKIP = "chain:**/XCUIElementTypeButton[`label == \"Skip\"`]",
            TODAY = "chain:**/XCUIElementTypeStaticText[`label == \"Today\"`]";


    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void skip()
    {
        this.waitForElementAndClick(SKIP);
        waitForElementPresent(TODAY, 30);
    }
}
