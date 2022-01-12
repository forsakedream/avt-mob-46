package lib.ui.mobile_mw;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    private static final String MENU_BUTTON = "css:label#mw-mf-main-menu-button",
                                LOG_IN_BUTTON = "xpath://*[contains(text(), 'Log in')]";


    static
    {
        MY_LIST = "css:a.menu__item--unStar";
    }

    public void openMenu(){
        waitForElementAndClick(MENU_BUTTON);
    }

    @Override
    public void goToLogin(){
        openMenu();
        tryClickElementWithFewAttempts(LOG_IN_BUTTON, 10);
    }

    @Override
    public void goToMyList(){
        openMenu();
        tryClickElementWithFewAttempts(MY_LIST, 10);
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
