package Practice;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class WebDriverMethodPractice {
	
	private static WebDriver driver;
	private static WebDriverWait mywait;
	private static TakesScreenshot ts;
	private static JavascriptExecutor js;
	
	@BeforeMethod
	public void OpenUrl() {
	
		driver=new ChromeDriver();
		mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		js=(JavascriptExecutor)driver;
		ts=(TakesScreenshot)driver;
		
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
		
	}
	
	@Test
	public void OpenWebPage() {
		
		WebElement CrossBtn=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='q7ywiQ']//span[@class='b3wTlE']")));
		
		js.executeScript("arguments[0].click();",CrossBtn);
		//By.xpath("//div[@class='q7ywiQ']//span[@class='b3wTlE']"
			
		List<WebElement> URLS=driver.findElements(By.xpath("//div[text()='GROUP COMPANIES']/following-sibling::a"));
		
		for(WebElement url:URLS) {
			url.click();
		}
		
		Set<String> WindowHandles=driver.getWindowHandles();
		
		for(String Values:WindowHandles) {
			driver.switchTo().window(Values);
			System.out.println(driver.getTitle()+" "+driver.getCurrentUrl());
			
			
			
			if(driver.getCurrentUrl().equals("https://www.shopsy.in/")) {
				// //div//div[text()='MOKSHA']
				
				driver.findElement(By.xpath("//div//input")).sendKeys("mo");
				//driver.findElement(By.xpath("//div//div[text()='MOKSHA']")).click();
				WebElement Search=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//div[text()='MOKSHA']")));
				js.executeScript("arguments[0].click();", Search);
				
			}
			driver.close();
		}
		
	}
	

}
