package homework;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EbaySearch2 {
	/*
	Open browser
	2. Go to https://ebay.com
	3. Search for wooden spoon
	4. Save the total number of results
	5. Click on link All under the categories on the left menu
	6. Verify that number of results is bigger than the number in step 4
	7. Navigate back to previous research results page
	8. Verify that wooden spoon is still displayed in the search box
	9. Navigate back to home page
	10. Verify that search box is blank
	*/
	
	WebDriver driver;
	@Before
	public void beforeMethod() {
		// open browser go to ebay
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://ebay.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void afterMethod() {
		driver.close();
	}
	
	@Test
	public void searchForWoodenSpoon() {
		String searchTerm = "wooden spoon";
		
		WebElement searchInput = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		searchInput.sendKeys(searchTerm, Keys.ENTER);
		
		String total = driver.findElement(By.xpath("//div[@class='srp-controls__control srp-controls__count']/h1/span[1]")).getText();
		int firstTotal = Integer.parseInt(total.replaceAll(",", ""));
//		System.out.println(firstTotal);
		
		WebElement allClick = driver.findElement(By.xpath("//ul[@class='srp-refine__category__list']/li[1]/a/span"));
		allClick.click();
		
		String total1 = driver.findElement(By.xpath("//div[@class='srp-controls__control srp-controls__count']/h1/span[1]")).getText();
		int secondTotal = Integer.parseInt(total1.replaceAll(",", ""));
//		System.out.println(secondTotal);
		
		if(secondTotal>firstTotal) {
			System.out.println("Second total number of all is bigger than first one. Verified");
		}else {
			System.out.println("Not verified");
		}
		
		driver.navigate().back();
		
		WebElement searchInput2 = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		String searchedItem = searchInput2.getAttribute("value");
		
		if(searchTerm.equals(searchedItem)) {
			System.out.println(searchTerm + " is still in search input.");
		}
		
		driver.navigate().back();
		
		WebElement searchInput3 = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		String searchedItemInputOnHomepage = searchInput3.getAttribute("value");
		
//		System.out.println(searchedItemInputOnHomepage);
		
		if(searchedItemInputOnHomepage.isEmpty()) {
			System.out.println("Homepage search box is blank.");
		}
	}
}
