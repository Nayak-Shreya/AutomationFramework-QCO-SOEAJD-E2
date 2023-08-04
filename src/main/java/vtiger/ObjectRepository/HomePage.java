package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	//Declaration
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;
	
	@FindBy(linkText = "Opportunities")
	private WebElement OpportunitiesLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdminImage;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;
	
	//Initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrganizationsLink() {
		return OrganizationsLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return OpportunitiesLink;
	}

	public WebElement getAdminImage() {
		return AdminImage;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}

	//Business Library
	/**
	 * This method will click on Organization link
	 */
	public void clickOnOrgLink()
	{
		OrganizationsLink.click();
	}
	
	/**
	 * This method will click on Contact link
	 */
	public void clickOnContactLink()
	{
		ContactsLink.click();
	}
	
	/**
	 * This method will click on Opportunities link
	 */
	public void clickOnOpportunitiesLink()
	{
		OpportunitiesLink.click();
	}
	
	/**
	 * This method will logout of application
	 * @param driver
	 * @throws Throwable
	 */
	public void logoutOfApp(WebDriver driver) throws Throwable
	{
		mouseHoverAction(driver, AdminImage);
		Thread.sleep(1000);
		SignOutLink.click();
	}
	

}
