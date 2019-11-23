package seleniumtests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
	private WebDriver driver;
	private static BasePage base = null;

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
	}

	public WebDriver getDriver() {
		this.init();
		return driver;
	}

	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

}
