package vtiger.OrganizationTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateNewOrganization;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateMultipleOrgWithIndustryTest extends BaseClass {
	
	@Test(dataProvider = "getData")
	public void createMultipleOrgWithIndustryTest(String ORG, String INDUSTRY) {
		
		//Read Data from Excel sheet - Test data
		String ORGNAME = ORG + jUtil.getRandomNumber();
		
		//Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLink();
		
		//Click on Create Organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookupImg();

		//Create Organization
		CreateNewOrganization cnop = new CreateNewOrganization(driver);
		cnop.createOrgWithIndustry(ORGNAME, INDUSTRY);
		
		//Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader=oip.getHeaderText();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader);
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable, IOException {

		return eUtil.readMultipleData("MultipleOrg");
	}

}
