package Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class DropDownPractice {
	
	
	
	@Test
	public void AutoSuggest() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		String text="Selenium";
		driver.findElement(By.xpath("//textarea[@class='gLFyf']")).sendKeys(text);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		List<WebElement> options=driver.findElements(By.xpath("//div[@class='wM6W7d'][@role='presentation']//b"));
		
		
		System.out.println(options.size());
		for(WebElement ele:options) {
			System.out.println(ele.getText());
			
			if(ele.getText().equalsIgnoreCase("interview questions"))
				ele.click();
		}
		
		driver.quit();
	}

	@Test
 void Hidden() {
		
		WebDriver driver=new ChromeDriver();
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
	
	
}
