package UI.AppiumUtilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jayway.jsonpath.JsonPath;

import GenericUtilities.Globals;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppiumUtil {

	WebDriver driver;

	public AppiumUtil(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	public WebDriver waitForContextAndSwitch(WebDriver driver, String context, int seconds) throws Exception {
		int currentSeconds = 0;
		while (currentSeconds < seconds && ((AppiumDriver) driver).getContextHandles().size() < 2) {
			Thread.sleep(1000);
			currentSeconds += 1;
		}
		Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();
		for (int i = 0; i < ((AppiumDriver) driver).getContextHandles().size(); i++) {
			if (contextNames.toArray()[i].toString().toLowerCase().contains(context.toLowerCase())) {
				context = contextNames.toArray()[i].toString();
				((AppiumDriver) driver).context(context);
				break;
			}
		}
		if (!(((AppiumDriver) driver).getContext().equals(context))) {
			throw new Exception("Context did not appear");
		}
		return driver;
	}

	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	public HashMap<String, Object> setCapabilities(String jsonPath) throws Exception {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Object capabilities = null;
		hashmap.put("automationName",
				JsonPath.read(AppiumUtil.readFileAsString(jsonPath), "$.automationName").toString());
		hashmap.put("platformName", JsonPath.read(AppiumUtil.readFileAsString(jsonPath), "$.platformName").toString());

		switch (Globals.EXECUTION_ENVIRONMENT.toLowerCase()) {
		case "local":
			if (!Globals.MOBILE_WEB) {
				if (Globals.TABLET) {
					capabilities = JsonPath.read(AppiumUtil.readFileAsString(jsonPath), "$.local.mobileapp.tablet[0]");
				} else {
					capabilities = JsonPath.read(AppiumUtil.readFileAsString(jsonPath), "$.local.mobileapp.mobile[0]");
				}
			} else if (Globals.MOBILE_WEB) {
				if (Globals.TABLET) {
					capabilities = JsonPath.read(AppiumUtil.readFileAsString(jsonPath), "$.local.mobileweb.tablet[0]");
				} else {
					capabilities = JsonPath.read(AppiumUtil.readFileAsString(jsonPath), "$.local.mobileweb.mobile[0]");
				}
			}
			String str = capabilities.toString().replace("{", "").replace("}", "");
			for (String keyValue : str.split(" *, *")) {
				String[] key = keyValue.split(" *= *");
				hashmap.put(key[0], key[1]);
			}
			break;
		case "cloud":
			// TODO : Once we have cloud configured, we will add the code here.
			break;
		}
		return hashmap;
	}

	public void switchToFrame(int frameIndex) {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public String webchatURL() throws Exception {
		return JsonPath.read(AppiumUtil.readFileAsString(Globals.ENVIRONMENT_JSON), "$.webchatUrl").toString();
	}

	public String getUrl() throws Exception {
		return JsonPath.read(AppiumUtil.readFileAsString(Globals.ENVIRONMENT_JSON), "$.appiumServerUrl").toString();
	}
}
