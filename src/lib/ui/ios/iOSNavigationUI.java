package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.WebElement;

public class iOSNavigationUI extends NavigationUI {
    static
    {
        MY_LIST = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public iOSNavigationUI(AppiumDriver<WebElement> driver) {
        super(driver);
    }
}
