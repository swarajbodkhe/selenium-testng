package Login;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.LandingPage;
import Pages.LoginPage;
import Pages.WordpressHome;
import seleniumtests.BasePage;
import seleniumtests.RepositoryManager;

public class LoginTest{
	
	WebDriver driver;
	LandingPage lp;
	LoginPage loginPg;
	WordpressHome wpHome;
	
	@Test
  public void LoginToWordpress() throws IOException {
		String uname=RepositoryManager.getConfig().getProperty("username");
		String pwd=RepositoryManager.getConfig().getProperty("password");
		lp.gotoWebPage();
		assertTrue(lp.isLandingPage());
		lp.clickOnLogin();
		loginPg.LoginToWP(uname, pwd, false);
		assertTrue(wpHome.IsWPHome());
		
  }
	
	@BeforeTest
	public void initBrowser() throws IOException {
		this.driver=BasePage.getBasePage().getDriver();
		lp=new LandingPage(this.driver);
		loginPg=new LoginPage(this.driver);
		wpHome=new WordpressHome(this.driver);
	}
	
	@AfterTest
	public void closeBrowser() {
		BasePage.getBasePage().closeBrowser();
	}
	
	
}
