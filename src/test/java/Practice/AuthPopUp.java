package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AuthPopUp {
	
	@Test
	public void AuthPopTest() {
		
		WebDriver driver=new ChromeDriver();
//		driver.get("http://the-internet.herokuapp.com/basic_auth");
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		String Text=driver.findElement(By.xpath("//div//p")).getText();
		System.out.println(Text);
	}

}
