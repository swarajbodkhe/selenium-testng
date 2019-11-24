package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Common.BasePage;
import Common.RepositoryManager;

public class LandingPage {
	@FindBy(how=How.XPATH,using="//li//a[text()='Log in']")
	private WebElement LandingPageLogin;
	
	private WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void gotoWebPage() throws IOException {
		this.driver.get(RepositoryManager.getConfig().getProperty("url"));
	}
	
	public boolean isLandingPage() {
		return driver.getTitle().contains("swarajwordpressautomation");
	}
	
	public void clickOnLogin() {
		BasePage.getBasePage().ClickUsingJS(this.LandingPageLogin);
	}
	
}
