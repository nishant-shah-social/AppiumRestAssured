package UI.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;

public class FAQSectionScreen extends BaseScreen {

	private WebDriver driver;

	@FindBy(css = ".section-faq")
	private WebElement sectionFaqTitle;

	@FindBy(xpath = "//a[@class='sdk-nav-icon']")
	private WebElement backCTA;

	public FAQSectionScreen(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	@Step("Selecting an FAQ")
	public void selectFaq() {
		wait.visibilityOf(sectionFaqTitle).click();
	}

	@Step("Scrolling to FAQ Feedback section")
	public void scrollToFaqFeedbackSection() {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				driver.findElement(By.xpath("//faq-feedback")));
		ele.findElement(By.cssSelector(".faq-feedback-buttons"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0]. scrollIntoView(true);",
				ele.findElement(By.cssSelector(".faq-feedback-buttons")));
	}

	@Step("Returning title of faq feedback question")
	public String faqFeedbackQuestionTitle() {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				driver.findElement(By.xpath("//faq-feedback")));
		return ele.findElement(By.cssSelector(".faq-feedback-question")).getText();
	}

	@Step("Tapping on Feedback option")
	public FAQSectionScreen tapFeedbackOption(String option) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				driver.findElement(By.xpath("//faq-feedback")));
		ele.findElement(By.cssSelector(".faq-feedback-negative")).click();
		return this;
	}

	@Step("Returning text of Feedback submitted")
	public String faqFeedbackSubmittedText() {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				driver.findElement(By.xpath("//faq-feedback")));
		return wait.visibilityOf(ele.findElement(By.cssSelector(".faq-feedback-submitted"))).getText();
	}

	@Step("Verifying Contact Us CTA is displayed")
	public boolean isContactUsCTADisplayed() {
		boolean isDisplayed = false;
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				driver.findElement(By.xpath("//faq-feedback")));
		try {
			ele.findElement(By.cssSelector(".faq-feedback-contact-btn"));
			isDisplayed = true;
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public void tapBackCTA() {
		backCTA.click();
	}

}
