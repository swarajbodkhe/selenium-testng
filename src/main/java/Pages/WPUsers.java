package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Common.BasePage;

public class WPUsers {
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//h1[@class='wp-heading-inline'][text()='Users']")
	WebElement UsersTitle;
	
	@FindBy(how=How.XPATH,using="//a[@class='page-title-action' and text()='Add New']")
	WebElement AddNew;

	@FindBy(how=How.ID,using="user_login")
	WebElement Username;
	
	@FindBy(how=How.ID,using="email")
	WebElement Email;
	
	@FindBy(how=How.ID,using="first_name")
	WebElement Fname;
	
	@FindBy(how=How.ID,using="last_name")
	WebElement Lname;

	@FindBy(how=How.ID,using="url")
	WebElement Website;

	@FindBy(how=How.XPATH,using="//button[text()='Show password']")
	WebElement ShowPassword;
	
	@FindBy(how=How.ID,using="pass1")
	WebElement Password;
	
	@FindBy(how=How.ID,using="send_user_notification")
	WebElement sendUserNotification;
	
	@FindBy(how=How.ID,using="role")
	WebElement Role;
	
	@FindBy(how=How.ID,using="createusersub")
	WebElement AddNewUser;
	
	@FindBy(how=How.XPATH,using="//table[contains(@class,'wp-list-table')]//tbody")
	WebElement AllUsersTable;
	
	@FindBy(how=How.ID,using="bulk-action-selector-top")
	WebElement SelectBulkActions;
	
	@FindBy(how=How.ID,using="doaction")
	WebElement ApplyAction;
	
	@FindBy(how=How.XPATH,using="//input[@value='Confirm Deletion']")
	WebElement ConfirmDeletion;
	
	public WPUsers(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createNewUser(boolean sendUserNotification,String...strings ) {
		
		this.AddNew.click();
		
		BasePage.getBasePage().waitForVisibility(this.Username);
		
		this.Username.sendKeys(strings[0]);
		this.Email.sendKeys(strings[1]);

		this.Fname.sendKeys(strings[2]);
		this.Lname.sendKeys(strings[3]);
		
		this.Website.sendKeys(strings[4]);
		
		this.ShowPassword.click();
		
		BasePage.getBasePage().waitForVisibility(this.Password);
		
		this.Password.clear();
		this.Password.sendKeys(strings[5]);
		
		if(!sendUserNotification) {
			this.sendUserNotification.click();
		}
		
		Select selectRole=new Select(this.Role);
		selectRole.selectByVisibleText(strings[6]);
		
		
		BasePage.getBasePage().waitForElementToBeClickable(this.AddNewUser);
		this.AddNewUser.click();
		
		BasePage.getBasePage().waitForVisibility(this.AddNew);
		
	}
	
	public int verifyUserIsTable(String criteria) {
		
		String[] records=criteria.split("\\|", 0);
		List<WebElement> rows;
		rows=this.AllUsersTable.findElements(By.xpath(".//tr"));
		boolean bFound=false;
		int RecordLoc=0;
		
		for(int i=1;i<=rows.size();i++) {
			for (String record : records) {
				String dataVal=".//tr["+i+"]//td[@data-colname='"+record.split("=",0)[0]+"']";
				String expectedTxt=record.split("=",0)[1];
				String actualtxt="";
				
				if(this.AllUsersTable.findElements(By.xpath(dataVal+"//a")).size()<=0){
					actualtxt=this.driver.findElement(By.xpath(dataVal)).getText();
				}else {
					actualtxt=this.driver.findElement(By.xpath(dataVal+"//a")).getText();
				}
				
				if(!actualtxt.equals(expectedTxt)) {
					bFound=false;
					break;
				}else {
					bFound=true;
				}
			}
			
			if(bFound) {
				RecordLoc=i;
				break;
			}			
		}
		
		return RecordLoc;
	}
	
	public boolean SelectUserInGrid(String criteria) {
		int row=this.verifyUserIsTable(criteria);
		
		this.AllUsersTable.findElement(By.xpath(".//tr["+ row +"]//th[@class='check-column']//input"))
		.click();
		
		return this.AllUsersTable.findElement(By.xpath(".//tr["+ row +"]//th[@class='check-column']//input"))
				.isSelected();
	}
	
	public void DeleteUserInGrid(String criteria) {
		this.SelectUserInGrid(criteria);
		Select selectBulkActions=new Select(this.SelectBulkActions);
		selectBulkActions.selectByVisibleText("Delete");
		this.ApplyAction.click();
		BasePage.getBasePage().waitForVisibility(this.ConfirmDeletion);
		this.ConfirmDeletion.click();
		BasePage.getBasePage().waitForVisibility(this.AllUsersTable);
	}
	
}
