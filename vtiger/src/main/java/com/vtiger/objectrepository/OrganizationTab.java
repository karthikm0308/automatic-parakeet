package com.vtiger.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationTab {
	
	public OrganizationTab(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="search_txt")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
	private WebElement searchInLst;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public WebElement getSearchInLst() {
		return searchInLst;
	}
	
	public WebElement getSearchEdt() {
		return searchEdt;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}
}
