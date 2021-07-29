package UI.PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.Actions;
import GenericUtilities.Waits;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BaseScreen {

	private WebDriver driver;
	protected Waits wait;
	protected Actions actions;

	public BaseScreen(WebDriver driver) {
		this.driver = driver;
		wait = new Waits(driver);
		actions = new Actions(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	public boolean isDisplayed(WebElement element) {
		boolean flag = false;
		try {
			if (!(driver instanceof IOSDriver)) {
				if (element.isDisplayed())
					flag = true;
			} else if (driver instanceof IOSDriver) {
				if (element.isEnabled())
					flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}
}
