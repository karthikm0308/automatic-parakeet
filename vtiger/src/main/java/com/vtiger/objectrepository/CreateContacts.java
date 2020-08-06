package com.vtiger.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericutils.WebDriverUtils;
/**
 * 
 * @author Karthik M
 *
 */
public class CreateContacts extends WebDriverUtils{
	
	WebDriver driver;
	public CreateContacts(WebDriver driver) {                             
		   this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement orgNameLookup;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	/**
	 * create contact with orgName
	 * @param conatctLastName
	 * @param orgData
	 * @param orgSearchFor
	 */
   public void createContact(String contactLastName , String orgData ,String orgSearchFor) {
	   lastNameEdt.sendKeys(contactLastName);
	   orgNameLookup.click();
	   switchToNewTab(driver, "specific_contact_account_address");
	   OrganizationTab orgp = new OrganizationTab(driver);
	   waitForTheElementVisibility(orgp.getSearchEdt(), driver);
	   orgp.getSearchEdt().sendKeys(orgData);
	   select(orgp.getSearchInLst(), orgSearchFor, false);
	   orgp.getSearchBtn().click();
	   String xpathOrgLink = "//a[text()='"+orgData+"']";
	   driver.findElement(By.xpath(xpathOrgLink)).click();
	   
	   switchToNewTab(driver, "Administrator - Contacts");
	   saveBtn.click();
   }
	/**
	 * create contact with mandatory details
	 * @param conatctLastName
	 */
   public void createContact(String conatctLastName ) {
	   lastNameEdt.sendKeys(conatctLastName);
	   saveBtn.click();
   }
   
   
	

}
