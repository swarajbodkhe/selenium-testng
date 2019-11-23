package Login;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import seleniumtests.BasePage;
import seleniumtests.RepositoryManager;

public class LoginTest extends BasePage{
	
	WebDriver driver;
			
	@Test
  public void verifyTitle() {
		assertTrue(driver.getTitle().contains("swarajwordpressautomation"));
  }
	
	
	@BeforeTest
	public void initBrowser() throws IOException {
		driver=BasePage.getBasePage().getDriver();
		driver.get(RepositoryManager.getConfig().getProperty("url"));
		//"https://swarajwordpressautomation.000webhostapp.com"
	}
	
	@AfterTest
	public void closeBrowser() {
		BasePage.getBasePage().closeBrowser();
	}
	
	
}
