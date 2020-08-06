package com.vtiger.objectrepository;

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

public class CreateOrganization extends WebDriverUtils {

	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name="industry")
	private WebElement industriesList;
	
	
	@FindBy(name="accounttype")
	private WebElement accountTypeList;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	/**
	 *  create  organization with mandatory details
	 * @param orgName
	 */
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	/**
	 * create  organization with below param details
	 * @param orgName
	 * @param orgType
	 * @param orgIndusties
	 */
	public void createOrg(String orgName , String orgType ,String orgIndustries ) {
		orgNameEdt.sendKeys(orgName);
		select(industriesList, orgIndustries,false);
		select(accountTypeList, orgType, false);
		saveBtn.click();
	}
	
	public CreateOrganization(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}
