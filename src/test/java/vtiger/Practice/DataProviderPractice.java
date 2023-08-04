package vtiger.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	
	@Test(dataProvider = "phones")
	
	public void addToCartTest(String name, int price, String model) {
		
		System.out.println("Phone is "+name+" price is "+price+" model is "+model);
	}
	
	@DataProvider(name="phones")
		public Object[][] getData(){
			Object[][] data = new Object[3][3];
			
			data[0][0] = "Iphone";
			data[0][1] = 20000;
			data[0][2] = "S13";
			
			data[1][0] = "Samsung";
			data[1][1] = 15000;
			data[1][2] = "A80";
			
			data[2][0] = "Vivo";
			data[2][1] = 10000;
			data[2][2] = "Y21";
			
			return data;
		}

}
