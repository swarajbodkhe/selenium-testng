package Common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriver driver;
	private static BasePage base = null;
	private WebDriverWait wt;
	
	public static BasePage getBasePage() {
		if (base == null) {
			return base = new BasePage();
		}
		return base;
	}

	private void init(){
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wt=new WebDriverWait(this.driver,20);
	}

	public WebDriver getDriver() {
		this.init();
		driver.manage().window().maximize();
		return driver;
	}

	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	public void ClickUsingJS(WebElement e) {
		wt.until(ExpectedConditions.elementToBeClickable(e));
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", e);
	}
	
	public void waitForVisibility(WebElement e) {
		wt.until(ExpectedConditions.visibilityOf(e));
	}
	
	public boolean IsOnPage(String pageTitle) {
		return this.driver.getTitle().contains(pageTitle);
	}
	
	public void waitForElementToBeClickable(WebElement e) {
		wt.until(ExpectedConditions.elementToBeClickable(e));
	}

}
