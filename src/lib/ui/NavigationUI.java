package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NavigationUI extends MainPageObject{
    public static final String
            MY_LIST = "//android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }
    public void goToMyList()
    {
        this.waitForElementAndClick(By.xpath(MY_LIST));
    }

}
