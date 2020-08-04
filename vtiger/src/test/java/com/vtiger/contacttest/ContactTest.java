package com.vtiger.contacttest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTest {
	
	@Test
	public void main() throws Throwable {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		
		String parentWindow=driver.getWindowHandle();
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains("Home - vtiger CRM 5 - Commercial Open Source CRM"));
		
		WebElement org = driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[contains(text(),'Organizations')]"));
		wait.until(ExpectedConditions.elementToBeClickable(org));
		org.click();
		wait.until(ExpectedConditions.titleContains("Organizations - vtiger CRM 5 - Commercial Open Source CRM"));
		
		WebElement create_org = driver.findElement(By.xpath("//img[@alt='Create Organization...']"));
		wait.until(ExpectedConditions.titleContains("Organizations - vtiger CRM 5 - Commercial Open Source CRM"));
		wait.until(ExpectedConditions.elementToBeClickable(create_org));
		create_org.click();
		
		wait.until(ExpectedConditions.titleContains("Organizations - vtiger CRM 5 - Commercial Open Source CRM"));
		driver.findElement(By.name("accountname")).sendKeys("TYSS");
		
		WebElement industry = driver.findElement(By.name("industry"));
		Select s=new Select(industry);
		s.selectByVisibleText("Banking");
		
		WebElement type= driver.findElement(By.name("accounttype"));
		Select s1=new Select(type);
		s1.selectByVisibleText("Customer");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		wait.until(ExpectedConditions.titleContains("Organizations - vtiger CRM 5 - Commercial Open Source CRM"));
		String org_name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		Assert.assertTrue(org_name.contains("TYSS"));
		
		WebElement contacts = driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[contains(text(),'Contacts')]"));
		wait.until(ExpectedConditions.elementToBeClickable(contacts));
		contacts.click();
		wait.until(ExpectedConditions.titleContains("Contacts - vtiger CRM 5 - Commercial Open Source CRM"));
		
		WebElement create_contact = driver.findElement(By.xpath("//img[@alt='Create Contact...']"));
		wait.until(ExpectedConditions.elementToBeClickable(create_contact));
		create_contact.click();
		
		wait.until(ExpectedConditions.titleContains("Contacts - vtiger CRM 5 - Commercial Open Source CRM"));
		
		driver.findElement(By.name("lastname")).sendKeys("Karthik");
		driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		for(String window:windowHandles)
		{
				driver.switchTo().window(window);
				String title=driver.getTitle();
				if(title.contains("specific_contact_account_address"))
					break;
		}
		
		driver.findElement(By.id("search_txt")).sendKeys("TYSS");
		WebElement select_org_name = driver.findElement(By.name("search_field"));
		Select s2=new Select(select_org_name);
		s2.selectByVisibleText("Organization Name");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("TYSS")).click();
		
		driver.switchTo().window(parentWindow);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		wait.until(ExpectedConditions.titleContains("Contacts - vtiger CRM 5 - Commercial Open Source CRM"));
		String contact_name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		Assert.assertTrue(contact_name.contains("Karthik"));
		
		driver.findElement(By.xpath("//td[@class='tabSelected']//a[contains(text(),'Contacts')]")).click();
		
		wait.until(ExpectedConditions.titleContains("Contacts - vtiger CRM 5 - Commercial Open Source CRM"));
		driver.findElement(By.name("search_text")).sendKeys("Karthik");
		WebElement searchfield = driver.findElement(By.id("bas_searchfield"));
		Select s3=new Select(searchfield);
		s3.selectByVisibleText("Last Name");
		
		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(5000);
		
		WebElement tableData = driver.findElement(By.xpath("//tr[contains(@id,'row_')]//a[contains(text(),'Karthik')]"));
		String act_tableData=tableData.getText();
		
		Assert.assertTrue(act_tableData.contains("Karthik"));
		
		WebElement tableData1 = driver.findElement(By.xpath("//tr[contains(@id,'row_')]//a[contains(text(),'TYSS')]"));
		String act_tableData1=tableData1.getText();
		
		Assert.assertTrue(act_tableData1.contains("TYSS"));
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'del')]"))));
		driver.findElement(By.xpath("//a[contains(text(),'del')]")).click();
		
		driver.switchTo().alert().accept();
		
		driver.findElement(By.name("search_text")).sendKeys("Karthik");
		
		WebElement searchfield1 = driver.findElement(By.id("bas_searchfield"));
		Select s4=new Select(searchfield1);
		s4.selectByVisibleText("Last Name");
		
		driver.findElement(By.name("submit")).click();
		
		WebElement no_contact = driver.findElement(By.xpath("//table[contains(@class,'lvt small')]//tr//span[contains(@class,'genHeaderSmall')]"));
		
		Assert.assertTrue(no_contact.getText().contains("No Contact Found"));
		
		WebElement org1 = driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[contains(text(),'Organizations')]"));
		org1.click();
		wait.until(ExpectedConditions.titleContains("Organizations - vtiger CRM 5 - Commercial Open Source CRM"));
		
		driver.findElement(By.name("search_text")).sendKeys("TYSS");
		
		WebElement searchfield2 = driver.findElement(By.id("bas_searchfield"));
		Select s5=new Select(searchfield2);
		s5.selectByVisibleText("Organization Name");
		
		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(5000);
		
		WebElement tableData2 = driver.findElement(By.xpath("//tr[contains(@id,'row_')]//a[contains(text(),'TYSS')]"));
		String act_tableData2=tableData2.getText();
		
		Assert.assertTrue(act_tableData2.contains("TYSS"));
		
		driver.findElement(By.xpath("//a[contains(text(),'del')]")).click();
		
		driver.switchTo().alert().accept();
		
		Thread.sleep(5000);
		
		driver.findElement(By.name("search_text")).sendKeys("TYSS");
		
		WebElement searchfield3 = driver.findElement(By.id("bas_searchfield"));
		Select s6=new Select(searchfield3);
		s6.selectByVisibleText("Organization Name");
		
		driver.findElement(By.name("submit")).click();
		
		WebElement no_org = driver.findElement(By.xpath("//table[contains(@class,'lvt small')]//tr//span[contains(@class,'genHeaderSmall')]"));
		
		Assert.assertTrue(no_org.getText().contains("No Organization Found"));
		
		WebElement contacts1 = driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[contains(text(),'Contacts')]"));
		wait.until(ExpectedConditions.elementToBeClickable(contacts1));
		contacts1.click();
		wait.until(ExpectedConditions.titleContains("Contacts - vtiger CRM 5 - Commercial Open Source CRM"));
		
		driver.findElement(By.name("search_text")).sendKeys("Karthik");
		
		WebElement searchfield4 = driver.findElement(By.id("bas_searchfield"));
		Select s7=new Select(searchfield4);
		s7.selectByVisibleText("Last Name");
		
		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(5000);
		
		WebElement no_contact1 = driver.findElement(By.xpath("//table[contains(@class,'lvt small')]//tr//span[contains(@class,'genHeaderSmall')]"));
		
		Assert.assertTrue(no_contact1.getText().contains("No Contact Found"));
		
		WebElement user_mouse_hover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act=new Actions(driver);
		act.moveToElement(user_mouse_hover).perform();
		
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		
		driver.close();
		
		
		
		
		
		
	}

}
