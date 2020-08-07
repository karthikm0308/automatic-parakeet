package com.vtiger.orgtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.genericutils.BaseClass;
import com.vtiger.objectrepository.CreateOrganization;
import com.vtiger.objectrepository.Home;
import com.vtiger.objectrepository.Organization;
import com.vtiger.objectrepository.OrganizationInfo;

public class OrganizationTest extends BaseClass  {
	
	String sheetName="Org";
	String orgDropdown="Organization Name";
	
	@Test(priority=1)
	public void createOrgTest() throws Throwable {
		
		/* read test script specific data*/
		excelLib.setExcelFileSheet(sheetName);
		String orgName = excelLib.getCellData(1, 2)+ "_"+ wLib.RandomNum();
		
		/* step 3 : navigate to Org page */
		Home hp = new Home(driver);
		hp.getOrgLink().click();

		/* step 4 : navigate to create new Org page */
		Organization orgp = new Organization(driver);
		orgp.getCreateOrgImg().click();

		/* step 5 : create Org */
		CreateOrganization createOrgP = new CreateOrganization(driver);
		createOrgP.createOrg(orgName);

		/* step 6 : verify the Org */
		OrganizationInfo orgInfo=new OrganizationInfo(driver);
		String actOrgName = orgInfo.getOrgSucessfullMsgText().getText();

		Assert.assertTrue(actOrgName.contains(orgName));

		
		
	}
	
	
	@Test(priority=2)
	public void CreateOrgAndDeleteOrgTest() throws Throwable {
		
		/* read test script specific data*/
		excelLib.setExcelFileSheet("Org");
		String orgName = excelLib.getCellData(4, 2)+ "_"+ wLib.RandomNum();

		
		/* step 3 : navigate to Org page */
		Home hp = new Home(driver);
		hp.getOrgLink().click();

		/* step 4 : navigate to create new Org page */
		Organization orgp = new Organization(driver);
		orgp.getCreateOrgImg().click();

		/* step 5 : create Org */
		CreateOrganization createOrgP = new CreateOrganization(driver);
		createOrgP.createOrg(orgName);

		/* step 6 : verify the Org */
		OrganizationInfo orgInfo=new OrganizationInfo(driver);
		String actOrgName = orgInfo.getOrgSucessfullMsgText().getText();

		Assert.assertTrue(actOrgName.contains(orgName));		
		
		/*step 7 : Delete the Org*/
		wLib.waitForElementandClick(hp.getOrgLink(), driver);
		orgp.getSearchEdt().sendKeys(orgName);
		wLib.select(orgp.getSearchInLst(),orgDropdown, false);
		wLib.waitForElementandClick(orgp.getSearchBtn(), driver);
		
		wLib.waitForElementandClick(orgp.deleteOrg(orgName),driver);

		wLib.alertAccept(driver);
		
		/* Step 8: Verify the deleted organization */
		orgp.getSearchEdt().sendKeys(orgName);

		wLib.select(orgp.getSearchInLst(), orgDropdown, false);

		wLib.waitForElementandClick(orgp.getSearchBtn(), driver);

		Assert.assertTrue(orgp.getNoOrg().getText().contains("No Organization Found"));
	}

}
