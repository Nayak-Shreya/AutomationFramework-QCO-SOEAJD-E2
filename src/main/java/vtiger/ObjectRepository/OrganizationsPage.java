package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	//Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement CreateOrgLookupImg;
	
	//Initialization
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateOrgLookupImg() {
		return CreateOrgLookupImg;
	}

	//Business Library
	/**
	 * This method will click on create org lookup image
	 */
	public void clickOnCreateOrgLookupImg()
	{
		CreateOrgLookupImg.click();
	}
	

}
