package vtiger.GenericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

/**
 * This class consists of all the basic configuration annotations for 
 * all the common actions
 * @author Pradeep Nayak
 *
 */
public class BaseClass {
	
	public JavaUtility jUtil = new JavaUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	
	public WebDriver driver = null;
	
	//Only used for listener to take screenshot
	public static WebDriver sdriver;
	
	@BeforeSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void bsConfig()
	{
		System.out.println("====== db connection successful ======");
	}
	
	//@Parameters("browser") //hold a value //cross browser execution
	//@BeforeTest //distributed parallel execution
	@BeforeClass(alwaysRun = true)
	public void bcConfig() throws Throwable
	{
		//Read browser name and URL from property File
		String BROWSER = pUtil.getDataFromProprtyFile("browser");
		String URL = pUtil.getDataFromProprtyFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " === Browser launched ===");

		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " === Browser launched ===");
		} else {
			System.out.println("==== invalid Browser name ====");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForElementsToLoad(driver);
		
		//Only used for listener to take screenshot
		sdriver=driver;

		//Load the URL
		driver.get(URL);
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable
	{
		//read username and passowrd from property file
		String USERNAME = pUtil.getDataFromProprtyFile("username");
		String PASSWORD = pUtil.getDataFromProprtyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("==== Login Successful ====");
	}


	@AfterMethod(alwaysRun = true)
	public void amConfig() throws Throwable
	{
		HomePage hp = new HomePage(driver);
		hp.logoutOfApp(driver);
		
		System.out.println("==== Logout Successful ====");
	}
		
	//@AfterTest //distributed parallel execution
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		System.out.println(" ========== Browser Closed ========");
	}
		
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("====== db connection closed ======");
	}

}
