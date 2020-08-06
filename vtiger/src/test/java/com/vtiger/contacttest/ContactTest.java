package com.vtiger.contacttest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.genericutils.BaseClass;
import com.vtiger.objectrepository.ContactInfoPage;
import com.vtiger.objectrepository.Contacts;
import com.vtiger.objectrepository.CreateContacts;
import com.vtiger.objectrepository.CreateOrganization;
import com.vtiger.objectrepository.Home;
import com.vtiger.objectrepository.Organization;
import com.vtiger.objectrepository.OrganizationInfo;

public class ContactTest extends BaseClass {

	String sheetName="Contact";
	String orgDropdown="Organization Name";
	String cntDropdown="Last Name";
	
	@Test(priority = 1)
	public void CreateOrgAndContactTest() throws Throwable {

		/* read test script specific data */
		excelLib.setExcelFileSheet(sheetName);
		String orgName = excelLib.getCellData(1, 2) + "_" + wLib.RandomNum();
		String contactLastName = excelLib.getCellData(1, 5) + "_" + wLib.RandomNum();

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

		AssertJUnit.assertTrue(actOrgName.contains(orgName));
		
		/* step 6 : navigate to Contact page */
		//Home hp1 = new Home(driver);
		wLib.waitForElementandClick(hp.getContactLink(), driver);

		/* step 7 : navigate to create new Contact page */
		Contacts cp = new Contacts(driver);
		cp.getcreateContactImg().click();
		
		/*step 8: create new Contact page*/
		CreateContacts ccnp = new CreateContacts(driver);
		ccnp.createContact(contactLastName, orgName, orgDropdown);

		/* Step 9: Verify the contact */
		ContactInfoPage cnctInfo=new ContactInfoPage(driver);
		String contact_name = cnctInfo.getContSucessfullMsgText().getText();

		AssertJUnit.assertTrue(contact_name.contains(contactLastName));

	}

	@Test(priority = 2)
	public void CreateAndDeleteOrgAndContactTest() throws Throwable {

		/* read test script specific data */
		excelLib.setExcelFileSheet(sheetName);
		String orgName = excelLib.getCellData(1, 2) + "_" + wLib.RandomNum();
		String contactLastName = excelLib.getCellData(1, 5) + "_" + wLib.RandomNum();

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

		AssertJUnit.assertTrue(actOrgName.contains(orgName));
		
		/* step 6 : navigate to Contact page */
		//Home hp1 = new Home(driver);
		wLib.waitForElementandClick(hp.getContactLink(), driver);

		/* step 7 : navigate to create new Contact page */
		Contacts cp = new Contacts(driver);
		cp.getcreateContactImg().click();
		
		/*step 8: create new Contact page*/
		CreateContacts ccnp = new CreateContacts(driver);
		ccnp.createContact(contactLastName, orgName, orgDropdown);

		/* Step 9: Verify the contact */
		ContactInfoPage cnctInfo=new ContactInfoPage(driver);
		String contact_name = cnctInfo.getContSucessfullMsgText().getText();

		AssertJUnit.assertTrue(contact_name.contains(contactLastName));
		String act_tableData1 = cp.findOrg(orgName).getText();

		AssertJUnit.assertTrue(act_tableData1.contains(orgName));

		/* Step 10: Delete the Contact */
		wLib.waitForElementandClick(hp.getContactLink(), driver);
		cp.getSearchText().sendKeys(contactLastName);
		wLib.select(cp.getSearchField(),cntDropdown, false);
		wLib.waitForElementandClick(cp.getSubmit(), driver);
		wLib.waitForElementandClick(cp.deleteContact(contactLastName), driver);

		wLib.alertAccept(driver);

		/* Step 10: Verify the Deleted Contact */
		cp.getSearchText().clear();
		cp.getSearchText().sendKeys(contactLastName);

		wLib.select(cp.getSearchField(), cntDropdown, false);

		wLib.waitForElementandClick(cp.getSubmit(), driver);

		AssertJUnit.assertTrue(cp.getNoContact().getText().contains("No Contact Found"));

		/* Step 10: Navigate to Organization and Search and Verify the organization */
		wLib.waitForElementandClick(hp.getOrgLink(),driver);

		orgp.getSearchEdt().sendKeys(orgName);

		wLib.select(orgp.getSearchInLst(), orgDropdown, false);

		wLib.waitForElementandClick(orgp.getSearchBtn(), driver);
		String act_tableData2 = orgp.getOrg(orgName).getText();

		AssertJUnit.assertTrue(act_tableData2.contains(orgName));

		/* Step 11: Delete the organization */
		wLib.waitForElementandClick(hp.getOrgLink(), driver);
		orgp.getSearchEdt().sendKeys(orgName);
		wLib.select(orgp.getSearchInLst(),orgDropdown, false);
		wLib.waitForElementandClick(orgp.getSearchBtn(), driver);
		
		wLib.waitForElementandClick(orgp.deleteOrg(orgName),driver);

		wLib.alertAccept(driver);

		/* Step 12: Verify the deleted organization */
		orgp.getSearchEdt().sendKeys(orgName);

		wLib.select(orgp.getSearchInLst(), orgDropdown, false);

		wLib.waitForElementandClick(orgp.getSearchBtn(), driver);

		AssertJUnit.assertTrue(orgp.getNoOrg().getText().contains("No Organization Found"));

		/* Step 13: Verify the deleted contact again */

		wLib.waitForElementandClick(hp.getContactLink(), driver);

		cp.getSearchText().sendKeys(contactLastName);

		wLib.select(cp.getSearchField(), cntDropdown, false);

		wLib.waitForElementandClick(cp.getSubmit(), driver);

		AssertJUnit.assertTrue(cp.getNoContact().getText().contains("No Contact Found"));

	}

}
