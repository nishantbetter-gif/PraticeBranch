package Practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class BrokenImages {
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void ImageTest() throws IOException {
		////h3/following-sibling::img
		/// 
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito--");
		//options.addArguments("--headless=new");
		
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/broken_images");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		List<WebElement> images=driver.findElements(By.xpath("//h3/following-sibling::img"));
		System.out.println(images.size());
		
		driver.findElement(By.xpath("//h3/following-sibling::img1")).click();
		
		for(WebElement img:images) {
			img.click();
			
			String imgurl=img.getAttribute("src");
			
			URL url=new URL(imgurl);
			
			HttpURLConnection HttpURLConnection = (HttpURLConnection)url.openConnection();
			HttpURLConnection.connect();
			
			int responsecode=HttpURLConnection.getResponseCode();
			
			System.out.println(responsecode);
			
			if(responsecode>=400)
				System.out.println("Broken");
			else
				System.out.println("Not Broken");
		}
		
	}

}
