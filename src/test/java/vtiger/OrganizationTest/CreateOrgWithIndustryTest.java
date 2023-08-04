package vtiger.OrganizationTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateNewOrganization;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateOrgWithIndustryTest extends BaseClass {
	
	@Test(groups = "RegressionSuite")
	public void createOrgWithOrgIndustry() throws Throwable {
	
	/* Create Organization */

	// Step 1: Read all the necessary data

	String ORGNAME = eUtil.getDataFromExcel("Organizations", 4, 2) + jUtil.getRandomNumber();;
	String INDUSTRY = eUtil.getDataFromExcel("Organizations", 4, 3);
	
	// Step 2: Click on Organizations Link
	HomePage hp = new HomePage(driver);
	hp.getOrganizationsLink().click();
			
	// Step 3: click on Create Organization look up image
	OrganizationsPage op = new OrganizationsPage(driver);
	op.getCreateOrgLookupImg().click();
			
	// Step 4: create Organization and save
	CreateNewOrganization cn = new CreateNewOrganization(driver);
	cn.createOrgWithIndustry(ORGNAME, INDUSTRY);

	// Step 5: Validate for Organization
	OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	String OrgHeader=oip.getHeaderText();
	Assert.assertTrue(OrgHeader.contains(ORGNAME));
	System.out.println(OrgHeader);
	
	}
	
	@Test
	public void sampleTest() {
		System.out.println("Sample");
	}
}
