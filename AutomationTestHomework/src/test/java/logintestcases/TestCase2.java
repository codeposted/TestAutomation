package logintestcases;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase2 {
	/*
	 * 1) Open the browser
		2) Enter the URL “http://practice.automationtesting.in/”
		3) Click on My Account Menu
		4) Enter incorrect username in username textbox
		5) Enter incorrect password in password textbox.
		6) Click on login button
		7) Proper error must be displayed(ie Invalid username) and prompt to enter login again
	 */
	
	WebDriver driver;
	String incorrectUsername = "notFake@email.com";
	String incorrectPassword = "Password123$&1564654694653";
	
	@Before
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("http://practice.automationtesting.in");
		driver.manage().window().maximize();
	}
	
	@Test
	public void loginWithIncorrectUsernameAndIncorrectPassword() {
		
		WebElement myAccountBtn = driver.findElement(By.id("menu-item-50"));
		myAccountBtn.click();
		
		WebElement loginUsernameInput = driver.findElement(By.xpath("//div[@class='u-columns col2-set']/div[1]/form/p//input[@id='username']"));
		WebElement loginPasswordInput = driver.findElement(By.xpath("//div[@class='u-columns col2-set']/div[1]/form/p//input[@id='password']"));
		WebElement loginBtn = driver.findElement(By.xpath("//div[@class='u-columns col2-set']/div[1]/form/p//input[@name='login']"));
		
		String loginPageUrl = driver.getCurrentUrl();
		
		loginUsernameInput.sendKeys(incorrectUsername);
		loginPasswordInput.sendKeys(incorrectPassword);
		loginBtn.click();
		
		String errorMessage = driver.findElement(By.xpath("//div[@class='page-content entry-content']/div/ul/li")).getText();
		
//		System.out.println(errorMessage);
		
		String pageAfterLoginFailed = driver.getCurrentUrl();
		
		if(!errorMessage.isEmpty() & loginPageUrl.equals(pageAfterLoginFailed)) {
			System.out.println("login failed with Incorrect username and incorrect password, showed error message -> " + errorMessage);
			System.out.println("User still in login page, test pass");
		}
	}
}
