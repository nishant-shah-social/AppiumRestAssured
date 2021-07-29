package helpshift.sdkxautomation.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import GenericUtilities.Globals;
import UI.AppiumUtilities.AppiumUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseTest {

	private WebDriver driver;

	@BeforeMethod
	public void initializeDriver() throws Exception {
		AppiumUtil appiumUtil = new AppiumUtil(driver);
		switch (Globals.PLATFORM.toLowerCase()) {
		case "android":
			driver = new AndroidDriver(new URL(appiumUtil.getUrl()),
					new DesiredCapabilities(appiumUtil.setCapabilities(Globals.ANDROID_CAPABILITIES_JSON)));
			break;
		case "ios":
			driver = new IOSDriver(new URL(appiumUtil.getUrl()),
					new DesiredCapabilities(appiumUtil.setCapabilities(Globals.IOS_CAPABILITIES_JSON)));
			break;
		case "web":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/Apps/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default: {
			// TODO : Need to replace the print statement with a log statement
			System.out.println("Cannot initialize the driver");
		}
		}
		driver.manage().timeouts().implicitlyWait(Globals.IMPLICIT_WAIT, TimeUnit.SECONDS);
		if (Globals.TABLET) {
			Globals.SCREEN_WIDTH = getDriver().manage().window().getSize().width;
			Globals.SCREEN_HEIGHT = getDriver().manage().window().getSize().height;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
