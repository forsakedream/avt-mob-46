package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String AppiumUrl = "http://127.0.0.1:4723/wd/hub/",
                                PLATFORM_ANDROID = "android",
                                PLATFORM_IOS = "ios",
                                PLATFORM_MOBILE_WEB = "mobile_web";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {

        URL URL = new URL(AppiumUrl);
        if (this.isAndroid()) {
            return new AndroidDriver<>(URL, this.getAndroidCapabilities());

        } else if (this.isIOS()) {
            return new IOSDriver<>(URL, this.getIOSCapabilities());

        } else if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        }

        else {
            throw new Exception("Can't get Appium driver from env variable: " + this.getPlatform());
        }
    }

    private DesiredCapabilities getAndroidCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "avd1");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("app", "/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 11");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("udid", "8D8EC757-448A-4BD7-83D2-014F5862E819");
        capabilities.setCapability("app", "/Users/dkylosov/IdeaProjects/JavaAppiumAutomation/apk/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions()
    {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        deviceMetrics.put("deviceMetrics", deviceMetrics);
        deviceMetrics.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) \n" +
                "\n" +
                "AppleWebKit/535.19 (KHTML, like Gecko) \n" +
                "\n" +
                "Chrome/18.0.1025.166 Mobile Safari/535.19");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=360,640");

        return chromeOptions;
    }

    private boolean isPlatform(String platform)
    {
        String env_platform = this.getPlatform();
        return env_platform.equals(platform);
    }

    public String getPlatform()
    {
        return System.getenv("PLATFORM");
    }

    public boolean isAndroid()
    {
        return this.isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return this.isPlatform(PLATFORM_IOS);
    }
    public boolean isMW()
    {
        return this.isPlatform(PLATFORM_MOBILE_WEB);
    }
}
