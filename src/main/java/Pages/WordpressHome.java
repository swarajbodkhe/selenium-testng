package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Common.BasePage;


public class WordpressHome {
	
	WebDriver driver;
	
	@FindBy(how=How.CLASS_NAME,using="notice-dismiss")
	WebElement dismissNoticeBtn;
	
	
	public WordpressHome(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean IsWPHome() {
		return this.driver.getTitle().equals("Dashboard ‹ swarajwordpressautomation — WordPress");
	}
	
	public void dismissNotice() {
		BasePage.getBasePage().waitForVisibility(this.dismissNoticeBtn);
		this.dismissNoticeBtn.click();
	}
	
	public void SelectMenu(String MenuName) {
		String menuXpath="//div[@class='wp-menu-name'][text()='"+MenuName+"']";
		BasePage.getBasePage().waitForVisibility(this.driver.findElement(By.xpath(menuXpath)));
		WebElement e=this.driver.findElement(By.xpath(menuXpath));
		e.click();
	}
	

}
