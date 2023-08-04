package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	//Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement ContactHeaderText;
	
	//Initialization
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}
	
	//Business Library
	/**
	 * This method will capture the header text and return it to caller
	 * @return
	 */
	public String getContactHeader() {
		return ContactHeaderText.getText();
	}

}
