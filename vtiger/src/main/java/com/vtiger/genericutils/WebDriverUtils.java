package com.vtiger.genericutils;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	private Select select;
	private Actions act;
	private WebDriverWait wait;
	
	/**
	 * This metod is used to return random numbers
	 * @return int
	 */
	public int RandomNum()
	{
		Random rand= new Random();
		return rand.nextInt(10000);
	}
	
	/**
	 * This method is used to wait till the page loads
	 * @param driver
	 */
	public void waitForThePageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to wait till the title of the page loads
	 * @param title
	 * @param driver
	 */
	
	public void waitForTitle(String title, WebDriver driver)
	{
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * This method is used to wait till the element is visible
	 * @param element
	 * @param driver
	 */
	public void waitForTheElementVisibility(WebElement element, WebDriver driver)
	{
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to wait till element is displayed on the webpage 
	 * @param element
	 * @param driver
	 * @return boolean
	 * @throws Throwable
	 */
	
	public boolean waitForAnyElement(WebElement element,WebDriver driver) throws Throwable
	{
		boolean flag=false;
		int count=0;
		while(count<30)
		{
			try {
				element.isDisplayed();
				flag=true;
				break;
			}
			catch (Throwable e) {
				count++;
				Thread.sleep(1000);
			}
		}
		return flag;
	}
	/**
	 * This method is used to wait till the WebElement is visble and the performs click operation
	 * @param element
	 * @param driver
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean waitForElementandClick(WebElement element, WebDriver driver) throws Throwable
	{
		boolean flag = false;
		int count =0;
		while(count < 30) {
		      try {
		    	  element.click();
		    	  flag=true;
		    	  break;
		      }catch(Throwable t) {
		    	  count++;
		    	  Thread.sleep(1000);
		      }
		}
		return flag;

	}
	/**
	 * This method is used for perform actions on options in select tag
	 * based on index	
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index)
	{
		select = new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method is used for perform actions on options in select tag
	 * based on value or visible text 
	 * True - Value, False - Visible Text	
	 * @param element
	 * @param value
	 * @param flag
	 */
	
	public void select(WebElement element, String value, boolean flag)
	{
		select = new Select(element);
		if(flag)
		{
			select.selectByValue(value);
		}
		else
		{
			select.selectByVisibleText(value);
		}
	}
	/**
	 * This method is used to switch from parent window to child window 
	 * and vice versa 
	 * @param driver
	 * @param pageTitle
	 */
	public void switchToNewTab(WebDriver driver, String pageTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			String winID=it.next();
			driver.switchTo().window(winID);
			String currentPageTitle = driver.getTitle();
		    if(currentPageTitle.contains(pageTitle)) 
		    {
		    	  break;
		    }
			
		}
	}
	/**
	 * This method is used to accept the alerts
	 * @param driver
	 */
	public void alertAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method is used to dismiss the alerts
	 * @param driver
	 */
	public void alertDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is used to perform mouse hover actions
	 * @param driver
	 * @param element
	 */
	
	public void mouseHoverElement(WebDriver driver, WebElement element)
	{
		act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used to perform double click
	 * @param driver
	 * @param element
	 */
	
	public void doubleClick(WebDriver driver, WebElement element)
	{
		act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This method is used to perform right click
	 * @param driver
	 * @param element
	 */
	
	public void rightClick(WebDriver driver, WebElement element)
	{
		act=new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * This method is used to switch to the frame based on frame index value
	 * @param driver
	 * @param frame
	 */
	
	public void switchToFrame(WebDriver driver, int frame)
	{
		driver.switchTo().frame(frame);
	}
	
	/**
	 * This method is used to switch to the frame based on based on ID or Name attribute of frame tag
	 * @param driver
	 * @param Id
	 */
	
	public void switchToFrame(WebDriver driver, String Id)
	{
		driver.switchTo().frame(Id);
	}
	/**
	 * This method is used to switch the frame based on WebElement
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}	

}
