package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class WordpressHome {
	
	WebDriver driver;
	
	public WordpressHome(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean IsWPHome() {
		return this.driver.getTitle().equals("Dashboard ‹ swarajwordpressautomation — WordPress");
	}
	

}
