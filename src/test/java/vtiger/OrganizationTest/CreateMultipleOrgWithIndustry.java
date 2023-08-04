package vtiger.OrganizationTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.CreateNewOrganization;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateMultipleOrgWithIndustry {

	ExcelFileUtility eUtil = new ExcelFileUtility();
	JavaUtility jUtil = new JavaUtility();
	PropertyFileUtility pUtil = new PropertyFileUtility();
	WebDriverUtility wUtil = new WebDriverUtility();

	@Test (dataProvider = "getData")
	public void createMultipleOrg(String ORG, String INDUSTRY) throws Throwable {

		WebDriver driver = null;

		// Step 1: Read all the necessary data

		/* Read data from property File - Common Data */
		String BROWSER = pUtil.getDataFromProprtyFile("browser");
		String URL = pUtil.getDataFromProprtyFile("url");
		String USERNAME = pUtil.getDataFromProprtyFile("username");
		String PASSWORD = pUtil.getDataFromProprtyFile("password");

		/* Read Data from Excel sheet - Test data */
		String ORGNAME = ORG + jUtil.getRandomNumber();

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
		hp.clickOnOrgLink();

		// Step 6: click on Create Organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookupImg();

		// Step 6: create Organization
		CreateNewOrganization cnop = new CreateNewOrganization(driver);
		cnop.createOrgWithIndustry(ORGNAME, INDUSTRY);

		// Step 8: Validate
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getHeaderText();
		if (OrgHeader.contains(ORGNAME)) {
			System.out.println("PASS");
			System.out.println(OrgHeader);
		} else {
			System.out.println("Fail");
		}

		// Step 9: Logout of Application
		hp.logoutOfApp(driver);

	}

	@DataProvider
	public Object[][] getData() throws Throwable, IOException {

		return eUtil.readMultipleData("MultipleOrg");
	}

}
