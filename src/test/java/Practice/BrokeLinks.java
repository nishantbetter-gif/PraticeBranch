package Practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.http.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokeLinks {
	
	@Test
	public void TestLinks() throws IOException {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		List<WebElement> links=driver.findElements(By.xpath("//li//a"));
		
		System.out.println(links.size());
		
		try {
			for(WebElement link:links) {
				
				
				String linkurl=link.getAttribute("href");
				
				URL myurl=new URL(linkurl);
				
				HttpURLConnection connection=(HttpURLConnection)myurl.openConnection();
				
				connection.connect();
				
				int responsecode=connection.getResponseCode();
				
				if(responsecode>=400)
				{
					System.out.println(linkurl+" is broken");
				}
				else
					System.out.println(linkurl+" is working");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
