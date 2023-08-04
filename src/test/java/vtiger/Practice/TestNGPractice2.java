package vtiger.Practice;

import org.testng.annotations.Test;

public class TestNGPractice2 {
	
	@Test(priority = 1)
	public void createCustomer() {
		System.out.println("Customer created");
	}
	
	@Test(priority = -2)
	public void modifyCustomer() {
		System.out.println("Customer modified");
	}
	
	@Test
	public void deleteCustomer() {
		System.out.println("Customer deleted");
	}

}
