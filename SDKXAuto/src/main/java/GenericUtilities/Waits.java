package GenericUtilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Waits {

	WebDriver driver;
	WebDriverWait wait;

	public Waits(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	public WebElement visibilityOf(WebElement webElement) {
		return wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public WebElement presenceOfElementLocated(By by) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement visibilityOfElementLocated(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void invisibilityOfElementLocated(By by) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void turnOnImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Globals.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	public void turnOffImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
}
