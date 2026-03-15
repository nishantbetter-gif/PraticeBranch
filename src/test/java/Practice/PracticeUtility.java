package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PracticeUtility {

	
	
	private static WebDriver driver;
	private static WebDriverWait mywait;
	
	private static JavascriptExecutor js;
	private static TakesScreenshot ts;
	
	private static Properties prop;
	private static URL myurl;
	
	@BeforeSuite
	public void ConfigSetup() {
		try {
			prop=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\src\\test\\java\\Practice\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@BeforeTest
	public void SetupBrowser() {
		
		try {
			myurl=new URL(prop.getProperty("Practiceurl"));
			
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			
			String Browser=prop.getProperty("browser");
			
			if(Browser.equalsIgnoreCase("Chrome"))
				driver=new ChromeDriver(options);
			
			driver.navigate().to(myurl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(
			        Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait")))
			);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void CheckBox() throws InterruptedException {
		
		List<WebElement> RBrn=driver.findElements(By.xpath("//form//input[@type='radio']"));
		
		for(WebElement btn:RBrn) {
			System.out.println(btn.getAttribute("value"));
			
			String name=btn.getAttribute("value");
			
			if(name.equalsIgnoreCase("Male"))
				btn.click();
			
			
		}
		
		
		List<WebElement> Cbox=driver.findElements(By.xpath("//form//input[@type='checkbox']"));
		
	for(WebElement cb:Cbox) {
	
	System.out.println(cb.getAttribute("Value"));
	cb.click();
	}
	
	Thread.sleep(5000);
	}
	
	
	@AfterTest
	public void Close() {
		
		driver.quit();
		
	}
	
	
	
}
