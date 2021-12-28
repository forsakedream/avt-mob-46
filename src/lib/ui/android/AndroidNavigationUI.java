package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.WebElement;

public class AndroidNavigationUI extends NavigationUI {

    static
    {
        MY_LIST = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }
}