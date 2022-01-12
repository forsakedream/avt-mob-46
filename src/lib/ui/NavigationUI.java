package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String MY_LIST;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
    public void goToMyList()
    {
        this.waitForElementAndClick(MY_LIST);
    }

}
