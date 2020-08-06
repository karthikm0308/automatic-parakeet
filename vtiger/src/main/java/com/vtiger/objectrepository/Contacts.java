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
public class Contacts extends WebDriverUtils{
	
	WebDriver driver=null;
	public Contacts(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactImg;
	
	@FindBy (name="search_text")
	private WebElement searchText;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchField;
	
	@FindBy(name="submit")
	private WebElement submit;
	
	@FindBy(xpath="//span[@class='genHeaderSmall']")
	private WebElement noContact;

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getNoContact() {
		return noContact;
	}

	public WebElement getSubmit() {
		return submit;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getSearchText() {
		return searchText;
	}

	public WebElement getcreateContactImg() {
		return createContactImg;
	}
	
	public WebElement findOrg(String orgName)
	{
		 return driver.findElement(By.linkText(orgName));
	}
	
	public WebElement deleteContact(String contactName)
	{
		
		return driver.findElement(By.xpath("//a[text()='" + contactName + "']//parent::td/following-sibling::td/a[text()='del']"));
	}

}
