package vtiger.Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicLocator {

	public static void main(String[] args) {
		
		String[] t1 = {"Laptops", "Smart Watches", "UPS", "Earrings"};
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		for(String s : t1) {
			driver.get("https://www.flipkart.com/");
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(String.format("//div[text()='%s']", s))));
			System.out.println(driver.findElements(By.xpath("//a[@class='IRpwTa']")).size());
		}
		driver.quit();
	}

}
