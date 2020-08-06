package com.vtiger.genericutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import com.vtiger.genericutils.DataBaseLib;
import com.vtiger.genericutils.ExcelLib;
import com.vtiger.genericutils.FileLib;
import com.vtiger.genericutils.WebDriverUtils;

/**
 * 
 * @author Karthik M
 *
 */


public class BaseClass {
	
	public DataBaseLib dbLib = new DataBaseLib();
	public ExcelLib excelLib = new ExcelLib();
	public FileLib fLib = new FileLib();
	public WebDriverUtils wLib = new WebDriverUtils();
	public WebDriver driver = null;
	
	@BeforeSuite
	public void configBS() throws Throwable
	{
		dbLib.connectDB(fLib.getProperty("dbFilePath"), fLib.getProperty("dbUrl"), fLib.getProperty("db_username"), fLib.getProperty("db_password"));
	}
	
	@BeforeClass
	public void configBC() throws Throwable{
		 String BROWSER = fLib.getProperty("browser");
		  
		 if(BROWSER.equals("chrome")) {
		   driver= new ChromeDriver();
		 } else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		 }else if(BROWSER.equals("ie")) {
				driver = new InternetExplorerDriver();
	     }else {
	    	 driver = new FirefoxDriver();
	     }
		 wLib.waitForThePageToLoad(driver);
	}
	
	@BeforeMethod
	public void configBM() throws Throwable {
		/* read data from property File */
		String USERNAME = fLib.getProperty("ui_username");
		String PASSWORD = fLib.getProperty("ui_password");
		String URL = fLib.getProperty("url");
	
		/*login to app*/
		driver.get(URL);
		
		/*step 2 : login*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
	}
	
	@AfterMethod
	public void configAm() {
		/*logout */
		
		/*step 1 : logout*/
		WebElement wb = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        wLib.mouseHoverElement(driver, wb);
		driver.findElement(By.linkText("Sign Out")).click();
	}
	
	@AfterClass
	public void configAC(){
		/*close the browser*/
		driver.close();
	}
	
	
	@AfterSuite
	public void confiAs() throws Throwable {
		/*disconnect to db*/
		dbLib.closeDB();
	}

}
