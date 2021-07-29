package GenericUtilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Actions {

	WebDriver driver;

	public Actions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	// This method handles device & tablet clicks
	public void click(WebElement webElement) {
		if (!Globals.TABLET) {
			if ((driver instanceof IOSDriver) && (!Globals.MOBILE_WEB)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				Map<String, Object> params = new HashMap<>();
				params.put("x", ((webElement.getSize().width / 2) + webElement.getLocation().x));
				params.put("y", ((webElement.getSize().height / 2) + webElement.getLocation().y));
				js.executeScript("mobile: tap", params);
			} else {
				webElement.click();
			}
		} else {
			int attachmentCTAWidth = driver.manage().window().getSize().width;
			int attachmentCTAHeight = driver.manage().window().getSize().height;
			int attachmentCTAXcoordinate = ((Globals.SCREEN_WIDTH - attachmentCTAWidth) / 2);
			int attachmentCTAYcoordinate = ((Globals.SCREEN_HEIGHT - attachmentCTAHeight) / 2);

			if (driver instanceof IOSDriver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				Map<String, Object> params = new HashMap<>();
				params.put("x", (webElement.getLocation().x + attachmentCTAXcoordinate));
				params.put("y", (webElement.getLocation().y + attachmentCTAYcoordinate));

				js.executeScript("mobile: tap", params);
			} else {
				webElement.click();
			}
		}
	}

}
