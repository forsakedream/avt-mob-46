package tests.iOS;

import lib.CoreTestCase;
import lib.ui.MainPageObject;

public class GetStartedTest extends CoreTestCase {

    public static final String SKIP = "chain:**/XCUIElementTypeButton[`label == \"Skip\"`]",
                               TODAY = "chain:**/XCUIElementTypeStaticText[`label == \"Today\"`]";
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    public void testPassThroughWelcome()
    {
        MainPageObject.waitForElementAndClick(SKIP);
        MainPageObject.waitForElementPresent(TODAY, 30);
    }
}
