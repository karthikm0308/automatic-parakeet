package com.vtiger.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericutils.FileLib;

/**
 * @author Karthik M
 */
public class Login {
	
	
	public Login(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement userNameTF;
	
	@FindBy(name="user_password")
	private WebElement userPasswordTF;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	public void loginToApp(String username , String password) {   
		userNameTF.sendKeys(username);
		userPasswordTF.sendKeys(password);
		loginBtn.click();
		
	}
	
	public void loginToApp() throws Throwable {  
		FileLib flib = new FileLib();
		userNameTF.sendKeys(flib.getProperty("ui_username"));
		userPasswordTF.sendKeys(flib.getProperty("ui_password"));
		loginBtn.click();
		
	}

}
