package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class NavigationUI extends MainPageObject{
    public static final String
            MY_LIST = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }
    public void goToMyList()
    {
        this.waitForElementAndClick(MY_LIST);
    }

}
