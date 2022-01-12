package lib;

import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.skipWelcomeScreenForIOS();
        this.openWikiPageForMobileWeb();
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

    protected void openWikiPageForMobileWeb()
    {
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Nothing");
        }
    }


}
