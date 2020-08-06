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
public class Organization extends WebDriverUtils {
	
	
	WebDriver driver=null;
	public Organization(WebDriver driver) {                             
		   this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgImg;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchInLst;

	@FindBy(name="submit")
	private WebElement searchBtn;
	
	@FindBy(xpath="//span[@class='genHeaderSmall']")
	private WebElement noOrg;

	public WebElement getCreateOrgImg() {
		return createOrgImg;
	}
	
	public WebElement getSearchInLst() {
		return searchInLst;
	}
	
	public WebElement getNoOrg() {
		return noOrg;
	}


	public WebElement getSearchEdt() {
		return searchEdt;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	public WebElement getOrg(String orgName)
	{
		return driver.findElement(By.linkText(orgName));
	}
	
	public WebElement deleteOrg(String orgName)
	{
		
		return driver.findElement(By.xpath("//a[text()='" + orgName + "']//parent::td/following-sibling::td/a[text()='del']"));
	}

}
