package vtiger.ContactsTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.CreateNewOrganization;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateContactsWithOrganizationUsingPOM {
	
	public static void main(String[] args) throws Throwable {
		
		/* Create Organization */

		// Create object of required Utilities
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		WebDriver driver = null;
		
		// Step 1: Read all the necessary data

		/* Read data from property File - Common Data */
		String BROWSER = pUtil.getDataFromProprtyFile("browser");
		String URL = pUtil.getDataFromProprtyFile("url");
		String USERNAME = pUtil.getDataFromProprtyFile("username");
		String PASSWORD = pUtil.getDataFromProprtyFile("password");
		
		/* Read Data from Excel sheet - Test data */
		String ORGNAME = eUtil.getDataFromExcel("Contacts", 4, 3) + jUtil.getRandomNumber();
		String LASTNAME = eUtil.getDataFromExcel("Contacts", 4, 2);
		
		// Step 2: Launch the browser - driver is acting based runtime data - RunTime polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " --- Browser launched");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " --- Browser launched");
		} else {
			System.out.println("invalid Browser name");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForElementsToLoad(driver);
		
		// Step 3: Load the URL
		driver.get(URL);
		
		// Step 4: Login to the Application
		LoginPage lp = new LoginPage(driver);		
		lp.loginToApp(USERNAME, PASSWORD); 
		
		// Step 5: Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		
		// Step 6: click on Create Organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupImg().click();
		
		// Step 7: create Organization and save
		CreateNewOrganization cn = new CreateNewOrganization(driver);
		cn.createOrganization(ORGNAME);
		
		// Step 8: Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getHeaderText();
		if (OrgHeader.contains(ORGNAME)) {
			System.out.println("PASS");
			System.out.println(OrgHeader);
		} else {
			System.out.println("Fail");
		}
		
		/* Create Contact using Contact */

		// step 9: click on contacts link
		hp.clickOnContactLink();
		
		// Step 10: Navigate to create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImage();
		
		// step 11: Create a contact with mandatory information
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContact(driver, LASTNAME, ORGNAME);
		
		// Step 12: Validate for Contact
		ContactInfoPage cip = new ContactInfoPage(driver);
		String ContactHeader = cip.getContactHeader();
		if (ContactHeader.contains(LASTNAME)) {
			System.out.println("PASS");
			System.out.println(ContactHeader);
		} else {
			System.out.println("Fail");
		}
		
		//Logout
		hp.logoutOfApp(driver);
		System.out.println("Logout successfull");
	}

}
