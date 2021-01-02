package homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginFleetManagementTool {
	
	/*
	User story: As a user, I should be able to login VyTrack app.

	test case1: login successfully
	1. go to VyTrack login page
	2. write username   data: storemanager52
	3.write password           UserUser123
	4. click login button
	5.Verify that the user is on the homepage
	
	test cases2: login invalid cridential
	1. go to login page
	2.write invalid usr name
	3.write invalid password
	4. click login bttn
	5.verify that user see error message "Invalid user name or password."
	*/
	
	WebDriver driver;
	
	@Before
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://app.vytrack.com/user/login");
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void test1() {
		driver.findElement(By.id("prependedInput")).sendKeys("storemanager52");
		
		driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
		
		driver.findElement(By.id("_submit")).click();
		
		String title = driver.getTitle();
		
		if(title.equals("Dashboard")) {
			System.out.println("User is on the homepage, test is successful");
		}
	}
	
	@Test
	public void test2() throws InterruptedException {
		driver.findElement(By.id("prependedInput")).sendKeys("wrongusername");
		
		driver.findElement(By.id("prependedInput2")).sendKeys("wrongpassword");
		
		driver.findElement(By.id("_submit")).click();
		
		Thread.sleep(3000);
		
		String error = driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/div[1]/div")).getText();
		
//		System.out.println(error);
		
		if(error.equals("Invalid user name or password.")) {
			System.out.println("Login failed as expected, and showed expected error message, test is successful");
		}
		
	}
	
	@After
	public void afterMethod() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
	

}
