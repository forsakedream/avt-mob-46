package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthPageObject extends MainPageObject{

    private static final String
                                LOGIN_INPUT = "css:input.loginText",
                                PASSWORD_INPUT = "css:input.loginPassword",
                                SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password);
    }

    public void login(String login, String password) {
        this.enterLoginData(login, password);
        this.waitForElementAndClick(SUBMIT_BUTTON);
    }
}
