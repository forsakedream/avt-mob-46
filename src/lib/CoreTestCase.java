package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.WebElement;

public class CoreTestCase extends TestCase {

    protected AppiumDriver<WebElement> driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.skipWelcomeScreenForIOS();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void skipWelcomeScreenForIOS()
    {
        if (Platform.getInstance().isIOS())
        {
            WelcomePageObject WelcomePage = new WelcomePageObject(driver);
            WelcomePage.skip();
        }
    }


}
