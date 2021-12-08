package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver<WebElement> driver;
    private static final String AppiumUrl = "http://127.0.0.1:4723/wd/hub/";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = this.getDriverByPlatform();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getPlatformCapabilities() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals("android")) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "avd1");
            capabilities.setCapability("platformVersion", "10");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("orientation", "PORTRAIT");
            capabilities.setCapability("app", "/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/org.wikipedia.apk");
        } else if (platform.equals("ios")) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 11");
            capabilities.setCapability("platformVersion", "15");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("udid", "C52AFB38-C24B-421A-B2A5-63437E83A4E0");
            capabilities.setCapability("app", "/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/Wikipedia.app");
        } else {
                throw new Exception("Can't get platform from env variable: " + platform);
        }
        return capabilities;
    }

    private AppiumDriver<WebElement> getDriverByPlatform() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = this.getPlatformCapabilities();

        if (platform.equals("android")) {
            return new AndroidDriver<>(new URL(AppiumUrl), capabilities);
        } else if (platform.equals("ios")) {
            return new IOSDriver<>(new URL(AppiumUrl), capabilities);
        } else {
            throw new Exception("Can't get Appium driver from env variable: " + platform);
        }
    }

}
