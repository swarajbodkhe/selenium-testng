package Login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Common.BasePage;
import Common.RepositoryManager;
import Pages.LandingPage;
import Pages.LoginPage;
import Pages.WPUsers;
import Pages.WordpressHome;

public class LoginTest{
	
	WebDriver driver;
	LandingPage lp;
	LoginPage loginPg;
	WordpressHome wpHome;
	WPUsers wpUsers;
	
	@Test(description = "01. Verify User is able to login to Wordpress as Admin",
			groups = {"Sanity"})
  public void LoginToWordpress() throws IOException {
		String uname=RepositoryManager.getConfig().getProperty("username");
		String pwd=RepositoryManager.getConfig().getProperty("password");
		lp.gotoWebPage();
		assertTrue(BasePage.getBasePage().IsOnPage("swarajwordpressautomation"));
		lp.clickOnLogin();
		loginPg.LoginToWP(uname, pwd, false);
		assertTrue(BasePage.getBasePage().IsOnPage("Dashboard"));	
  }
	
	@Test(description = "Create a new WP Editor",dependsOnMethods = {"LoginToWordpress"})
	public void CreateANewUser() {
		wpHome.SelectMenu("Users");
		assertTrue(BasePage.getBasePage().IsOnPage("Users"));
		wpUsers.createNewUser(false, "autobot123","a@aaa.com","auto","bot","","+-eggFruit**","Editor");
	}
	
	@Test(description="Verify new user appears in Users Table"
			,dependsOnMethods = {"CreateANewUser"})
	public void verifyUserInUserTable() {
		wpHome.SelectMenu("Users");
		assertTrue(BasePage.getBasePage().IsOnPage("Users"));
		assertTrue(wpUsers.verifyUserIsTable("Username=autobot123|Name=auto bot|Email=a@aaa.com|Role=Editor")>0);
	}

	@Test(description = "Verify Admin is able to delete users from table"
			,dependsOnMethods = {"verifyUserInUserTable"})
	public void DeleteUserInUserTable() {
		wpHome.SelectMenu("Users");
		assertTrue(BasePage.getBasePage().IsOnPage("Users"));
		assertTrue(wpUsers.verifyUserIsTable("Username=autobot123|Name=auto bot|Email=a@aaa.com|Role=Editor")>0);
		wpUsers.DeleteUserInGrid("Username=autobot123|Name=auto bot|Email=a@aaa.com|Role=Editor");
		assertTrue(wpUsers.verifyUserIsTable("Username=autobot123|Name=auto bot|Email=a@aaa.com|Role=Editor")==0);
	}
	
	@BeforeTest
	public void initBrowser() throws IOException {
		this.driver=BasePage.getBasePage().getDriver();
		lp=new LandingPage(this.driver);
		loginPg=new LoginPage(this.driver);
		wpHome=new WordpressHome(this.driver);
		wpUsers=new WPUsers(this.driver);
	}
	
	@AfterTest
	public void closeBrowser() {
		BasePage.getBasePage().closeBrowser();
	}
	
	
}
