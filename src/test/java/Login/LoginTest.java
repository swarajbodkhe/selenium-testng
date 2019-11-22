package Login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
  
	public WebDriver driver;
	
	@Test
  public void verifyTitle() {
		assertTrue(driver.getTitle().contains("swarajwordpressautomation"));
  }
	
	
	@BeforeTest
	public void initBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://swarajwordpressautomation.000webhostapp.com");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	
}
