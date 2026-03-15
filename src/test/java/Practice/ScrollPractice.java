package Practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class ScrollPractice {
	
	
	private static WebDriver driver;
	private static WebDriverWait mywait;
	private static TakesScreenshot ts;
	private static JavascriptExecutor js;
	private static  Properties prop;
	private static URL myurl;
	private static ChromeOptions options;
	
	@BeforeSuite
	public void configSetup() throws IOException {
		
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\src\\test\\java\\Practice\\config.properties");
		prop.load(fis);
	}
	
	@BeforeMethod
	public void  BrowserSetup() throws MalformedURLException {
		
		myurl=new URL("https://www.geeksforgeeks.org/software-testing/selenium-handling-checkbox/");
		options=new ChromeOptions();
		options.addArguments("--disable-notificaitons");
		options.addArguments("--disable-infobars");
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
		String browser=prop.getProperty("browser");
		
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver(options);
			driver.navigate().to(myurl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
			
		}
		else
			throw new IllegalArgumentException("Invalid Browser");
		
		js=(JavascriptExecutor)driver;
		ts=(TakesScreenshot)driver;
		
	}
	
	@Test(priority=1)
	public void ScrollPage() throws IOException {
		
		js.executeScript("window.scrollBy(0,1000)");
		System.out.println(js.executeScript("return window.pageYOffset;"));
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File target=new File("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\ScreenShot\\ScrollNew.png");
		
		
		FileHandler.copy(src, target);
	}

	@Test(priority=3)
	public void ScrollElement() throws IOException {
		
		WebElement Image=driver.findElement(By.xpath("//img[@alt='selenium-2']"));
		
		js.executeScript("arguments[0].scrollIntoView();", Image);
		System.out.println(js.executeScript("return window.pageYOffset;"));
		
		File src=Image.getScreenshotAs(OutputType.FILE);
		
		File target=new File("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\ScreenShot\\Image.png");
		
		FileHandler.copy(src, target);
		
	}
	
	@Test(priority=4)
	public void ScrollBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println(js.executeScript("return window.pageYOffset;"));
	}
	
	@AfterMethod
	public void close() {
		
		driver.quit();
	}
}
