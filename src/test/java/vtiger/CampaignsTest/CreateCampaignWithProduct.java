package vtiger.CampaignsTest;

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

public class CreateCampaignWithProduct {

	public static void main(String[] args) throws Throwable {
		
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		WebDriver driver = null;
		
		String BROWSER = pUtil.getDataFromProprtyFile("browser");
		String URL = pUtil.getDataFromProprtyFile("url");
		String USERNAME = pUtil.getDataFromProprtyFile("username");
		String PASSWORD = pUtil.getDataFromProprtyFile("password");
		
		String CAMPNAME = eUtil.getDataFromExcel("Campaigns", 4, 2);
		String PRODNAME = eUtil.getDataFromExcel("Campaigns", 4, 3);
		String TYPE = eUtil.getDataFromExcel("Campaigns", 4, 4);
		String STATUS = eUtil.getDataFromExcel("Campaigns", 4, 5);

		
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

		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/* Create Product */
		
		driver.findElement(By.linkText("Products")).click();

		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

		driver.findElement(By.name("productname")).sendKeys(PRODNAME);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/* Create Campaign with Product */
		
		driver.findElement(By.xpath("(//a[@href=\"javascript:;\"])[1]")).click();
		driver.findElement(By.name("Campaigns")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		driver.findElement(By.name("campaignname")).sendKeys(CAMPNAME);
		
		WebElement campTypeDropdown = driver.findElement(By.name("campaigntype"));
		wUtil.handleDropDown(campTypeDropdown, TYPE);
		
		WebElement campStatusDropdown = driver.findElement(By.name("campaignstatus"));
		wUtil.handleDropDown(campStatusDropdown, STATUS);

		driver.findElement(By.xpath("//input[@name='product_name']/following-sibling::img[@title='Select']")).click();
		
		wUtil.switchToWindow(driver, "Products");
		driver.findElement(By.name("search_text")).sendKeys(PRODNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + PRODNAME + "']")).click(); // dynamic xpath
		wUtil.switchToWindow(driver, "Campaigns");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


		// Logout
		WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, AdminImg);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successfull");


		

	}

}
