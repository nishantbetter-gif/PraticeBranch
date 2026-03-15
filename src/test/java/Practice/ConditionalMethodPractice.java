package Practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ConditionalMethodPractice {
	
	private static WebDriver driver;
	private static WebDriverWait mywait;
	private static TakesScreenshot ts;
	private static JavascriptExecutor js;
	private static Properties prop;
	
	@BeforeSuite(description="Setting Config")
	public void ConfigSetup() throws IOException {
		
		try {
			prop=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\src\\test\\java\\Practice\\config.properties");
			prop.load(fis);
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found "+e.getLocalizedMessage());
		}
		
		
	}
	
	@BeforeTest
	public void OpenBrowser() {
		
		String Browser=prop.getProperty("browser");
		if(Browser.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver();
		else if(Browser.equalsIgnoreCase("Edge"))
			driver=new EdgeDriver();
		else if(Browser.equalsIgnoreCase("Firefox"))
			driver=new FirefoxDriver();
		else
			throw new IllegalArgumentException("Invalid Browser");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-infobars");
		

		driver = new ChromeDriver(options);
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
		
	}
	
	@Test(priority=1)
	public void VerifLogo() {
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Website for automation practice']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/login']")).isDisplayed());
		ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File Target=new File("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\ScreenShot\\Chrome.png");
		
		src.renameTo(Target);
		
		WebElement Logo=driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));

			src=Logo.getScreenshotAs(OutputType.FILE);
		
			Target=new File("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\ScreenShot\\logo.png");
		
			src.renameTo(Target);
		
	}

	
	@AfterTest
	public void Close() {
		driver.quit();
	}
	
}
