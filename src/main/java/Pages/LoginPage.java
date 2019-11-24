/***********************************************************************************************
Here I am using Page Object Model with Page Factory
Page object model is creating object repository for all UI elements and creating corresponding 
page class for each page which contains the element locators as well as methods/keywords
that are relevant to the page.
Page factory lets us initialize the elements of the page without using FindElement/s methods but 
by using attributes like FindBy/s. 
This improves the readability as well as reusability of code. 
**************************************************************************************************/

package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Common.BasePage;

public class LoginPage {
	@FindBy(how=How.XPATH,using="//a[text()='Log in']")
	private WebElement LandingPageLogin;
	
	@FindBy(how=How.ID,using="user_login")
	private WebElement Username;
	
	@FindBy(how=How.ID,using="user_pass")
	private WebElement Password;
	
	@FindBy(how=How.ID,using="rememberme")
	private WebElement RememberMe;
	
	@FindBy(how=How.ID,using="wp-submit")
	private WebElement LoginBtn;
	private WebDriver driver;
	private LandingPage lp;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		lp=new LandingPage(driver);
	}
	
	public void LoginToWP(String UserName,String Password,boolean bRememberMe){
		BasePage.getBasePage().waitForVisibility(this.Username);
		this.Username.sendKeys(UserName);
		this.Password.sendKeys(Password);
		if(bRememberMe) {
			this.RememberMe.click();
		}
		this.LoginBtn.click();
	}
	
	
	
	
	
	
	

}
