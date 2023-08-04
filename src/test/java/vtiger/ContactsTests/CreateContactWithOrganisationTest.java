package vtiger.ContactsTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.CreateNewOrganization;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

@Listeners(vtiger.GenericUtilities.ListenerImplementationClass.class)
public class CreateContactWithOrganisationTest extends BaseClass{

	@Test(groups = "SmokeSuite")
	public void createContactWithOrgTest() throws Throwable {

		/* Create Organization */

		// Step 1: Read all the necessary data

		String ORGNAME = eUtil.getDataFromExcel("Contacts", 4, 3) + jUtil.getRandomNumber();
		String LASTNAME = eUtil.getDataFromExcel("Contacts", 4, 2);

		// Step 2: Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLink();

		// Step 3: click on Create Organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookupImg();

		// Step 4: create Organization
		CreateNewOrganization cn = new CreateNewOrganization(driver);
		cn.createOrganization(ORGNAME);

		// Step 5: Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader=oip.getHeaderText();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader);

		/* Create Contact using Organization */

		// step 6: click on contacts link - 500 - update 
		hp.clickOnContactLink();

		// Step 7: Navigate to create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImage();

		// step 8: Create a contact with mandatory information
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContact(driver, LASTNAME, ORGNAME);

		// Step 9: Validate for Organization
		ContactInfoPage cip = new ContactInfoPage(driver);
		String ContactHeader=cip.getContactHeader();
		Assert.assertTrue(ContactHeader.contains(LASTNAME));
	    System.out.println(ContactHeader);
		
	}
	
	@Test
	public void sampleTest() {
		
		//Assert.fail();
		System.out.println("Sample");
	}

}
