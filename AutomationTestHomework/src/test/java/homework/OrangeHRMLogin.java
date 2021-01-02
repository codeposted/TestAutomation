package homework;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMLogin {
	/*
	 * This should retest your java skills, lets see how many of you can do this. 

		1. Go on OrangeHRM (https://opensource-demo.orangehrmlive.com/index.php/dashboard)
		2. Login with valid credentials ( Username : Admin | Password : admin123 )
		3. Confirm you're logged in by getting the current URL, and using the 
		Java Substring to extract the page name and that is equal to "dashboard"
	 */
	
	WebDriver driver;
	
	String pageName = "dashboard"; 
	
	int index = pageName.length();
	
	@Before
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void loginWithValidCredentials() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		String currentUrl = driver.getCurrentUrl();
		
//		System.out.println(currentUrl);
    	
		String dashbord = "This is HRM: " + currentUrl.substring(currentUrl.length()-index, currentUrl.length());
		
		System.out.println(dashbord);
	}
}
