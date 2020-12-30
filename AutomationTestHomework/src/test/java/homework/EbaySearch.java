package homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EbaySearch {
	
	/*
	Task:
	Go to amazon  https://www.amazon.com/
	then  Go to Ebay   https://www.ebay.com/
	Enter a search term - “shoes”
	Click on search button
	Verify title contains search term
	*/
	
	WebDriver driver;
	
	@Before
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.amazon.com");
		driver.get("https://www.ebay.com");
	}
	
	@Test
	public void test1() {
		driver.findElement(By.id("gh-ac")).sendKeys("shoes");
		driver.findElement(By.id("gh-btn")).click();
		
		String title = driver.getTitle();
		
		if (title.contains("shoes")) {
			System.out.println("Title contains search term.");
		} else {
			System.out.println("Title does not contain search term.");
		}
	}
	
	@After
	public void afterMethod() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
}
