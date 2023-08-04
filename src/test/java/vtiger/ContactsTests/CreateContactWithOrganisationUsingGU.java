package vtiger.ContactsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateContactWithOrganisationUsingGU {
	
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

		// Step 2: Launch the browser - driver is acting based runtime data - RunTime
		// polymorphism
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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 5: Click on Organizations Link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 6: click on Create Organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// Step 7: create Organization
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		// Step 8: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 9: Validate for Organization
		String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (OrgHeader.contains(ORGNAME)) {
			System.out.println("Organization Created");
			System.out.println(OrgHeader);
		} else {
			System.out.println("Fail");
		}

		/* Create Contact using Contact */

		// step 10: click on contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 11: Navigate to create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 12: Create a contact with mandatory information
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();

		// Step 13: switch to child window
		wUtil.switchToWindow(driver, "Accounts");

		// Step 14: search for Organization
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);

		driver.findElement(By.name("search")).click();

		driver.findElement(By.xpath("//a[text()='" + ORGNAME + "']")).click(); // dynamic xpath

		// Step 15: switch the control back to parent window
		wUtil.switchToWindow(driver, "Contacts");

		// Step 16: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 17: Validate for Contact
		String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (ContactHeader.contains(LASTNAME)) {
			System.out.println("PASS");
			System.out.println(ContactHeader);
		} else {
			System.out.println("Fail");
		}
		
		//Logout
		WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, AdminImg);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successfull");
		

	}


}
