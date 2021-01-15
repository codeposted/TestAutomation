package homework;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertAutomate {
	/*
	 * 1. Navigate to URL : https://www.seleniumeasy.com/test/javascript-alert-box-demo.html
		2. Scroll down to third Alert box ( Click for promot Box )  click the the button ( use Actions class ) 
		3 pupop will display and enter the message in box ( your name )  click OK button
		4 After click OK button, you will see message below EX : You have entered 'Techcircle' !
		Verify use ( asserttion ) Assert message has displyed.
		Also i would like to see other 
		assertion is : before message call : Click below button for prompt box. is different than new message, Expected TRUE. 
	 */
	
	WebDriver driver;
	String expectedMsg = "You have entered 'TechCircle' !";
	
	@Before
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}
	
	@Test
	public void clickPromptBox() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		Actions act = new Actions(driver);
		WebElement propmpBoxBtn = driver.findElement(By.xpath("//div[@class='col-md-6 text-left']/div[3]/div[2]/button[@class='btn btn-default btn-lg']"));
		act.click(propmpBoxBtn).perform();
		
//		other way below
//		act.moveToElement(propmpBoxBtn).click().perform();	
		
		Alert alertBox = driver.switchTo().alert();
		alertBox.sendKeys("TechCircle");
		
		Thread.sleep(3000);
		alertBox.accept();
		
		String actualMsg = driver.findElement(By.id("prompt-demo")).getText();
		
		Assert.assertEquals(actualMsg, expectedMsg);
		
//		message was empty before enter message to alert box, so new message appeared
		Assert.assertTrue(!actualMsg.isEmpty());
		
	}
}
