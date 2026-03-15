package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NavigationMethodPractice {
	
	private static WebDriver driver;
	private static WebDriverWait mywait;
	private static TakesScreenshot ts;
	private static JavascriptExecutor js;
	private static URL url;
	private static Properties prop;
	
	@BeforeSuite
	public void configSetup() throws IOException {
		
		try {
			prop=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\src\\test\\java\\Practice\\config.properties");
			prop.load(fis);
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@BeforeTest
	public void BrowserOpen() throws MalformedURLException {
		
		
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-infobars");
		
		String Browser=prop.getProperty("browser");
		url=new URL("https://automationexercise.com/");
		
		if(Browser.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver(options);
		
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		js=(JavascriptExecutor)driver;
		ts=(TakesScreenshot)driver;
		
		
		
	}

	@Test
	public void LoginTest() {
		
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		
		
		
		WebElement EmailInput=mywait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@action='/login']//input[@type='email']")));
		
		String Email=prop.getProperty("email");

		js.executeScript("arguments[0].value=arguments[1];", EmailInput, Email);
		
		String Password=prop.getProperty("password");
		
		WebElement PasswordInput=driver.findElement(By.xpath("//form[@action='/login']//input[@type='password']"));
		
		js.executeScript("arguments[0].value=arguments[1];", PasswordInput,Password);
		
		WebElement LoginBtn=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='/login']//button[@type='submit']")));
		
		js.executeScript("arguments[0].click();",LoginBtn);
		
		System.out.println("Paased");
		
		
	}
	
	@AfterTest
	public void Close() {
//		driver.close();
	}
}
