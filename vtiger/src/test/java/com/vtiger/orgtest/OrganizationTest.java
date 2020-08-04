package com.vtiger.orgtest;

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

public class OrganizationTest {
	
	@Test
	public static void main() {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		
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
		
		driver.findElement(By.xpath("//td[@class='tabSelected']//a[contains(text(),'Organizations')]")).click();
		
		driver.findElement(By.name("search_text")).sendKeys("TYSS");
		
		WebElement searchfield = driver.findElement(By.id("bas_searchfield"));
		Select s3=new Select(searchfield);
		s3.selectByVisibleText("Organization Name");
		
		driver.findElement(By.name("submit")).click();
		
		WebElement tableData = driver.findElement(By.xpath("//table[contains(@class,'lvt small')]//tr[contains(@class,'lvtColData')]//a[contains(.,'TYSS')]"));
		String act_tableData=tableData.getText();
		
		Assert.assertTrue(act_tableData.contains("TYSS"));
		
		driver.findElement(By.xpath("//table[contains(@class,'lvt small')]//tr[contains(@class,'lvtColData')]//a[contains(.,'TYSS')]/../following-sibling::td/a[contains(.,'del')]")).click();
		
		driver.switchTo().alert().accept();
		
		driver.findElement(By.name("search_text")).sendKeys("TYSS");
		
		WebElement searchfield1 = driver.findElement(By.id("bas_searchfield"));
		Select s4=new Select(searchfield1);
		s4.selectByVisibleText("Organization Name");
		
		driver.findElement(By.name("submit")).click();
		
		WebElement no_org = driver.findElement(By.xpath("//table[contains(@class,'lvt small')]//tr//span[contains(@class,'genHeaderSmall')]"));
		
		Assert.assertTrue(no_org.getText().contains("No Organization Found"));
		
		WebElement user_mouse_hover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act=new Actions(driver);
		act.moveToElement(user_mouse_hover).perform();
		
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		
		driver.close();
	}

}
