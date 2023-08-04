package vtiger.Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleAlert {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String name = "GTKT";
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		driver.switchTo().alert().accept();
		System.out.println(driver.findElement(By.xpath("//p[text()='You successfully clicked an alert']")).isDisplayed()==true);
		
		driver.findElement(By.xpath("//button[text()='Click fo  r JS Confirm']")).click();
		driver.switchTo().alert().accept();
		System.out.println(driver.findElement(By.xpath("//p[text()='You clicked: Ok']")).isDisplayed()==true);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		driver.switchTo().alert().dismiss();
		System.out.println(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).isDisplayed()==true);

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		driver.switchTo().alert().sendKeys(name);
		driver.switchTo().alert().accept();
		System.out.println(driver.findElement(By.xpath("//p[text()='You entered: "+name+"']")).isDisplayed()==true);

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		driver.switchTo().alert().sendKeys(name);
		driver.switchTo().alert().dismiss();
		System.out.println(driver.findElement(By.xpath("//p[text()='You entered: null']")).isDisplayed()==true);

	}

}
