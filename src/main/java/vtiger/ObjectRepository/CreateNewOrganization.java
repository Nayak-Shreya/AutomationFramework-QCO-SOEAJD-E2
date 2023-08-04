package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganization extends WebDriverUtility {
	
	//Declaration
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement industryDrpdwn;
	
	@FindBy(name = "accounttype")
	private WebElement typeDrpdwn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialization
	public CreateNewOrganization(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getIndustryDrpdwn() {
		return industryDrpdwn;
	}

	public WebElement getTypeDrpdwn() {
		return typeDrpdwn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method will create organization
	 * @param ORGNAME
	 * @param iNDUSTRY 
	 */
	public void createOrganization(String ORGNAME)
	{
		orgNameEdt.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create organization with industry
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createOrgWithIndustry(String ORGNAME, String INDUSTRY)
	{
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(industryDrpdwn, INDUSTRY);
		saveBtn.click();
	}

}
