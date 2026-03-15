package Practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class CompleteLoginPage {
	
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
			myurl=new URL("https://proleed.academy/exercises/selenium/automation-practice-form-with-radio-button-check-boxes-and-drop-down.php");
			
			
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
	
	@Test(priority=1)
	public void FillDetails() throws InterruptedException {
		
		WebElement SelectDropDown=driver.findElement(By.xpath("//div[@class='col-sm-2 selectcol']//select"));
		
		Select sel=new Select(SelectDropDown);
		List<WebElement> PrefixDropDown=sel.getOptions();
		
		for(WebElement Prefix:PrefixDropDown) {
			System.out.println(Prefix.getText());
			if(Prefix.getText().equalsIgnoreCase("Mrs."))
				sel.selectByVisibleText("Mrs.");
		}
		
		
		
		List<WebElement> Accounts=driver.findElements(By.xpath("//input[@name='accounttype']"));
		
		for(WebElement Acc:Accounts) {
			
			if(Acc.getAttribute("value").equalsIgnoreCase("pension"))
				Acc.click();
			
			System.out.println(Acc.getAttribute("value"));
		}
		
		List<WebElement> CheckBox=driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		for(WebElement cbox:CheckBox) {
			System.out.println(cbox.getAttribute("id"));
			
			if(cbox.getAttribute("id").equalsIgnoreCase("passport")||cbox.getAttribute("id").equalsIgnoreCase("studentid")) {
				cbox.click();
			}
			
		}
		
		WebElement Month = driver.findElement(By.xpath("//div[@class='col-sm-3 selectcol']//select[@id='dob_month']"));
	
		Select selmonth=new Select(Month);
		
		for(WebElement mon:selmonth.getOptions()) {
			System.out.println(mon.getText());
		}
		
		Thread.sleep(4000);
	}
	
	@Test(priority=2)
	public void Scroll() throws InterruptedException, IOException {
		
		js=(JavascriptExecutor)driver;
		ts=(TakesScreenshot)driver;

		// scroll by pixels
		js.executeScript("window.scrollBy(0,1000)");
		System.out.println(js.executeScript("return window.pageYOffset;"));
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File target=new File("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\ScreenShot\\Scroll.png");
		
		   FileHandler.copy(src, target);
		   
		 
		Thread.sleep(4000);
		
		driver.navigate().refresh();
	}
//	
//	@Test(priority=3)
//	public void ScrollElement() throws IOException {
//		 // WebElement Scroll
//		   WebElement Text=driver.findElement(By.xpath("//div[@class='container']//h2[text()='View All Selenium Exercises']"));
//		   
//		   
//		   js.executeScript("arguments[0].scrollIntoView();",Text);
//			System.out.println(js.executeScript("return window.pageYOffset;"));
//			File src=Text.getScreenshotAs(OutputType.FILE);
//			
//			File target=new File("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\ScreenShot\\Text.png");
//			
//			   FileHandler.copy(src, target);
//		
//	}
	
//	@AfterTest
//	public void Close() {
//		driver.quit();
//		
//	}
//	

}
