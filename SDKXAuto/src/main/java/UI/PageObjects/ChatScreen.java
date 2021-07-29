package UI.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.Globals;
import UI.AppiumUtilities.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

public class ChatScreen extends BaseScreen {

	private WebDriver driver;

	@FindBy(xpath = "//div[@class='hs-message__item']")
	private List<WebElement> messages;

	@FindBy(xpath = "//textarea[@class='hs-chat-footer__text-area']")
	private WebElement sendMessageEditText;

	@FindBy(id = "hs-web-sdk-iframe")
	private WebElement webViewIframe;

	@FindBy(xpath = "//a[@class='hs-chat-footer__submit']")
	private WebElement submitMessageButton;

	@FindBy(className = "hs-message-list__typing-indicator")
	private WebElement agentTypingIndicator;

	@FindBy(xpath = "//button[@class='hs-chat-footer__new-conversation-button']")
	private WebElement newConversation;

	@FindBy(xpath = "//textarea[@placeholder='Leave us additional feedback']")
	private WebElement feedbackNote;

	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement feedbackSubmit;

	@FindBy(xpath = "//label[@for='upload-file']")
	private WebElement attachmentCTA;

	@iOSXCUITFindBy(id = "Browse")
	private WebElement browseCTA;

	@iOSXCUITFindBy(id = "TestAttachments")
	private WebElement testAttachmentsFolder;

	@FindBy(xpath = "(//div[@class='hs-message hs-message--right']//*[@class='hs-message__item hs-message__image-attachment'])[last()]")
	private WebElement userAttachedImage;

	@FindBy(xpath = "(//div[@class='hs-message hs-message--right']//*[@class='hs-message__item hs-message__file-attachment']//*[@class='hs-message__file-attachment-size-detail'])[last()]")
	private WebElement userAttachedFileSize;

	@FindBy(xpath = "(//div[@class='hs-message hs-message--left']//*[@class='hs-message__item hs-message__image-attachment'])[last()]")
	private WebElement agentAttachedImage;

	@FindBy(xpath = "(//div[@class='hs-message hs-message--left']//*[@class='hs-message__item hs-message__file-attachment']//*[@class='hs-message__file-attachment-size-detail'])[last()]")
	private WebElement agentAttachedFileSize;

	private By userAttachment = By.xpath(
			"(//div[@class='hs-message hs-message--right']//*[@class='hs-message__item hs-message__image-attachment'])[last()]");

	private By agentAttachment = By.xpath(
			"(//div[@class='hs-message hs-message--left']//*[@class='hs-message__item hs-message__image-attachment'])[last()]");

	private By fileUploadingGif = By.xpath("//*[@aria-label='Your attachment, Uploading']");

	private By searchBarNative = By.xpath("//XCUIElementTypeSearchField[@name='Search']");

	public ChatScreen(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	@Step("Getting message count")
	public int getMessagesCount() {
		return messages.size();
	}

	@Step("Returning text of first message")
	public String getFirstMessageText() {
		return messages.get(0).getText();
	}

	@Step("Returning text of last message")
	public String getLastMessageText() {
		int messageCount = messages.size();
		return messages.get(messageCount - 1).getText();
	}

	@Step("Entering the input message")
	public void sendMessage(String message) {
		Actions action = new Actions(driver);
		if (driver instanceof IOSDriver) {
			action.sendKeys(message).build().perform();
		} else {
			sendMessageEditText.sendKeys(message);
		}
		submitMessageButton.click();
	}

	@Step("Waiting for the issue to be created")
	public void waitForIssueToBeCreated(String message) {
		wait.visibilityOfElementLocated(By.xpath("//div[text()='" + message + "']"));
	}

	@Step("Waiting for resolution question")
	public void waitForResolutionQuestion() {
		wait.visibilityOfElementLocated(By.xpath("//strong[text()='Did we answer all your questions?']"));

	}

	@Step("Tapping {answer} to resolution question")
	public void answerToResolutionQuestion(String answer) {
		WebElement element = (WebElement) driver.findElement(By.xpath("//button[text()='" + answer + "']"));
		element.click();
	}

	@Step("Waiting for the issue to be resolved")
	public void waitForIssueToBeResolved() {
		wait.visibilityOf(newConversation);

	}

	@Step("Waiting for the prompt asking for the chat experience")
	public void waitForRatingExperiencePrompt() {
		wait.visibilityOfElementLocated(By.xpath("//strong[text()='How would you rate your chat experience?']"));
	}

	@Step("Giving {rating} star rating")
	public void clickRating(String rating) {
		WebElement element = (WebElement) driver
				.findElement(By.xpath("//*[name()='svg'][@title='" + rating + " stars']"));
		if (driver instanceof IOSDriver) {
			element.click();
		} else {
			Actions builder = new Actions(driver);
			builder.click(element).build().perform();
		}
	}

	@Step("Giving the feedback : {feedback}")
	public void enterFeedback(String feedback) {
		feedbackNote.sendKeys(feedback);
	}

	@Step("Tapping on feedback submit CTA")
	public void clickFeedbackSubmit() {
		feedbackSubmit.click();
	}

	@Step("Tapping on reply footer")
	public ChatScreen tapOnReplyFooter() {
		wait.presenceOfElementLocated(By.xpath("//textarea[@class='hs-chat-footer__text-area']"));
		actions.click(sendMessageEditText);
		return this;
	}

	@Step("Waiting for new conversation CTA to appear")
	public void waitForNewConversationButtonVisibility() {
		wait.visibilityOf(newConversation);
	}

	@Step("Waiting for SI Search Result to be visible")
	public void waitForSISearchResultToBeVisible() {
		wait.visibilityOfElementLocated(By.xpath("//*[@class='hs-nested-picker__option-item-search-result']"));
	}

	@Step("Waiting for reply footer to be visible")
	public void waitForReplyFooterToBeVisible() {
		wait.visibilityOf(sendMessageEditText);
	}

	public void siSearchTap(int siIndex) {
		List<WebElement> li = driver
				.findElements(By.xpath("//*[@class='hs-nested-picker__option-item-search-result']"));
		li.get(siIndex).click();
	}

	@Step("Creating an issue, entering input text ")
	public String createIssue() throws InterruptedException {
		String message = Globals.USER_MESSAGE;
		sendMessage(message);
		waitForIssueToBeCreated(message);
		return message;
	}

	@Step("Tapping Attachment CTA")
	public ChatScreen tapAttachmentCTA() {
		actions.click(attachmentCTA);
		return this;
	}

	@Step("Selecting an attachment")
	public void selectAttachment(int i) {
		testAttachmentsFolder.click();
		driver.findElement(By.xpath("//XCUIElementTypeCell[" + i + "]")).click();
	}

	@Step("Tapping Browse CTA")
	public void tapBrowse() {
		browseCTA.click();
	}

	@Step("Sending file {fileName} as attachment")
	public void sendAttachment(String fileName) throws Exception {
		AppiumUtil util = new AppiumUtil(driver);
		if (driver instanceof AppiumDriver) {
			util.waitForContextAndSwitch((AppiumDriver) driver, "native", 30);
			if (driver instanceof IOSDriver) {
				tapBrowse();
				wait.visibilityOfElementLocated(searchBarNative).sendKeys(fileName);
				if (fileName.equals("testDocument")) {
					wait.visibilityOfElementLocated(By.xpath("//*[@name='" + fileName + ".doc']/..")).click();
				} else {
					wait.visibilityOfElementLocated(By.xpath("//*[@name='" + fileName + "']/..")).click();
				}
			} else if (driver instanceof AndroidDriver) {
				wait.visibilityOfElementLocated(By.id("com.android.documentsui:id/option_menu_search")).click();
				driver.findElement(By.xpath("//*[contains(@text,'Search')]")).sendKeys(fileName);
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + fileName + "')]")).click();
			}
			util.waitForContextAndSwitch((AppiumDriver) driver, "webview", 30);
		} else {
			wait.visibilityOf(sendMessageEditText);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.getElementById('upload-file').style.display='block';");
			driver.findElement(By.id("upload-file")).sendKeys(fileName);
			executor.executeScript("document.getElementById('upload-file').style.display='none';");
		}
	}

	@Step("Verifying user has attached the image successfully")
	public boolean verifyImageIsAttachedFromUser() throws InterruptedException {
		boolean isImageDisplayed = false;
		wait.invisibilityOfElementLocated(fileUploadingGif);
		String style = wait.visibilityOfElementLocated(userAttachment).getAttribute("style");
		isImageDisplayed = style.contains("url") && style.contains("Expires") && style.contains("Signature")
				&& style.contains("Key-Pair-Id");
		int height = Integer.parseInt(userAttachedImage.getCssValue("height").replace("px", ""));
		int width = Integer.parseInt(userAttachedImage.getCssValue("width").replace("px", ""));
		isImageDisplayed = ((height > 0) && (width > 0));
		return isImageDisplayed;
	}

	@Step("Verifying user has attached the file successfully")
	public boolean verifyFileIsAttachedFromUser() throws InterruptedException {
		boolean isFiledAttached = false;
		wait.invisibilityOfElementLocated(fileUploadingGif);
		int fileSize = Integer.parseInt(userAttachedFileSize.getText().replaceAll("[\\D]", ""));
		isFiledAttached = (fileSize > 0);
		return isFiledAttached;
	}

	@Step("Verifying agent has sent the as attachment successfully")
	public boolean verifyImageIsAttachedFromAgent() {
		boolean isImageDisplayed = false;
		String style = wait.visibilityOfElementLocated(agentAttachment).getAttribute("style");
		isImageDisplayed = style.contains("url") && style.contains("Expires") && style.contains("Signature")
				&& style.contains("Key-Pair-Id");
		int height = Integer.parseInt(agentAttachedImage.getCssValue("height").replace("px", ""));
		int width = Integer.parseInt(agentAttachedImage.getCssValue("width").replace("px", ""));
		isImageDisplayed = ((height > 0) && (width > 0));
		return isImageDisplayed;
	}

	@Step("Verifying agent has sent the file as attachment successfully")
	public boolean verifyFileIsAttachedFromAgent() throws InterruptedException {
		boolean isFiledAttached = false;
		int fileSize = Integer.parseInt(agentAttachedFileSize.getText().replaceAll("[\\D]", ""));
		isFiledAttached = (fileSize > 0);
		return isFiledAttached;
	}
}
