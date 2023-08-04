package vtiger.ProductsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateProductWithVendor {

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
		String PRODNAME = eUtil.getDataFromExcel("Products", 4, 2) + jUtil.getRandomNumber();
		String VENDORNAME = eUtil.getDataFromExcel("Products", 4, 3);

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

		// Step 5: Click on More button for Vendors Link
		driver.findElement(By.xpath("(//a[@href=\"javascript:;\"])[1]")).click();
		driver.findElement(By.name("Vendors")).click();

		// Step 6: click on Create Product look up image
		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();

		// Step 7: Create Vendor
		driver.findElement(By.name("vendorname")).sendKeys(VENDORNAME);
		
		// Step 8: Choose 303-interest-income in GL Account Drop down
		WebElement glAccountDropdown = driver.findElement(By.name("glacct"));
		Select s = new Select(glAccountDropdown);
		s.selectByValue("303-Interest-Income");

		// Step 9: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


		/* Create Product using Vendor */

		// step 10: click on products link
		driver.findElement(By.linkText("Products")).click();

		// Step 11: Navigate to create product look up image
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

		// step 12: Create a product with mandatory information
		driver.findElement(By.name("productname")).sendKeys(PRODNAME);

		driver.findElement(By.xpath("//input[@name='vendor_id']/following-sibling::img[@title='Select']")).click();

		// Step 13: switch to child window
		wUtil.switchToWindow(driver, "Vendors");

		// Step 14: search for vendor
		driver.findElement(By.name("search_text")).sendKeys(VENDORNAME);

		driver.findElement(By.name("search")).click();

		driver.findElement(By.xpath("//a[text()='" + VENDORNAME + "']")).click(); // dynamic xpath

		// Step 15: switch the control back to parent window
		wUtil.switchToWindow(driver, "Products");

		// Step 16: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


		// Logout
		WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, AdminImg);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successfull");

	}

}
