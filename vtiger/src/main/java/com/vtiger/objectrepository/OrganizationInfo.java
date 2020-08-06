package com.vtiger.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * 
 * @author Karthik M
 *
 */


public class OrganizationInfo {
	public OrganizationInfo(WebDriver driver) {                             
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orgSucessfullMsgText;

	public WebElement getOrgSucessfullMsgText() {
		return orgSucessfullMsgText;
	}

}
