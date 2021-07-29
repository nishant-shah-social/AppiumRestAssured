package UI.PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jayway.jsonpath.JsonPath;

import GenericUtilities.Globals;
import UI.AppiumUtilities.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

public class HomeScreen extends BaseScreen {

	private WebDriver driver;

	@FindBy(xpath = "//button[@aria-label='Open Chat']")
	private WebElement openChatCTA;

	@AndroidFindBy(id = "open_helpshift")
	@iOSXCUITFindBy(id = "Chat with us!")
	private WebElement chatWithUsButton;

	@AndroidFindBy(id = "com.helpshift.liteyagami:id/show_faq_section")
	@iOSXCUITFindBy(id = "Show FAQ Section!")
	private WebElement showFaqSection;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Enter FAQ Section ID']")
	private WebElement faqSectionIdModal;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	private WebElement faqSectionIdModalTextField;

	@iOSXCUITFindBy(id = "GO")
	private WebElement faqSectionIdModalGoCTA;

	@iOSXCUITFindBy(id = "Install")
	private WebElement installCTA;

	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Do you want to download')]")
	private WebElement downloadAttachmentsModal;

	@iOSXCUITFindBy(id = "Download")
	private WebElement downloadCTA;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Downloads']")
	private WebElement downloadsFolder;

	@iOSXCUITFindBy(id = "TestAttachments, Folder")
	private WebElement testAttachmentsFolder;

	public HomeScreen(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	@Step("Navigating to chat screen")
	public ChatScreen navigateToChatScreen() {
		chatWithUsButton.click();
		return new ChatScreen(driver);
	}

	@Step("Tapping Install CTA")
	public void tapInstall() {
		installCTA.click();
	}

	@Step("Tapping Open chat CTA ")
	public HomeScreen openChat() {
		openChatCTA.click();
		return this;
	}

	@Step("Navigating to FAQ Section screen")
	public FAQSectionScreen navigateToFAQSection() {
		showFaqSection.click();
		if (driver instanceof IOSDriver) {
			if (isDisplayed(faqSectionIdModal)) {
				faqSectionIdModalTextField.sendKeys("399");
				faqSectionIdModalGoCTA.click();
			}
		}
		return new FAQSectionScreen(driver);
	}

	@Step("Downloading the attachments")
	public void downloadAttachments() throws Exception {
		if (driver instanceof AndroidDriver) {
			String command = "adb push " + Globals.ATTACHMENTFILES_PATH + " /storage/emulated/0/Download";
			Runtime.getRuntime().exec(command);
		} else if (driver instanceof IOSDriver) {
			String attachmentURLsJson = JsonPath
					.read(AppiumUtil.readFileAsString(Globals.ENVIRONMENT_JSON), "$.attachment").toString();
			((IOSDriver) driver).activateApp("com.apple.mobilesafari");
			String str = attachmentURLsJson.toString().replace("{", "").replace("}", "");
			for (String keyValue : str.split(" *, *")) {
				String[] url = keyValue.split("=", 2);
				driver.get(url[1]);
				downloadCTA.click();
			}
			((IOSDriver) driver).activateApp("com.helpshift.demo");
		}
	}

	@Step("Open chat screen")
	public ChatScreen openChatScreen() throws Exception {
		AppiumUtil appiumUtil = new AppiumUtil(driver);
		if (Globals.MOBILE_WEB || (!(driver instanceof AppiumDriver))) {
			driver.get(appiumUtil.webchatURL());
			appiumUtil.switchToFrame(1);
			openChat();
			appiumUtil.switchToFrame(0);
		} else {
			navigateToChatScreen();
			appiumUtil.waitForContextAndSwitch((AppiumDriver) driver, "webview", 30);
			appiumUtil.switchToFrame(0);
		}
		return new ChatScreen(driver);
	}
}
