package vtiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPractice {
	
	@Test
	public void createCustomer() {
		//Assert.fail(); //purposefully fail the script
		System.out.println("Customer created");
	}
	
	@Test(dependsOnMethods = "createCustomer")
	public void modifyCustomer() {
		System.out.println("Customer modified");
	}
	
	@Test
	public void deleteCustomer() {
		System.out.println("Customer deleted");
	}

}
