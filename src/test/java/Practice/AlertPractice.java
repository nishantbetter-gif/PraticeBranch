package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlertPractice {
	
	private static WebDriver driver;
	private static WebDriverWait mywait;
	private static Properties prop;
	private static TakesScreenshot ts;
	private static JavascriptExecutor js;
	private static URL myurl;
	private static ChromeOptions options;
	
	
	@BeforeSuite
	public void ConfigSetup() throws IOException {
		
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Ashish\\eclipse-workspace\\Java\\PracticeSelenium\\src\\test\\java\\Practice\\config.properties");
		
		prop.load(fis);
		
	}

	@BeforeMethod
	public void OpenBrowser() throws MalformedURLException {
	
		myurl=new URL("https://demo.automationtesting.in/Alerts.html");
		options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-infobars");
		
		String brow=prop.getProperty("browser");
		if(brow.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver(options);
			driver.navigate().to(myurl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
			
		}	
	}
	
	@Test(priority=1)
	public void NormalAlert() throws InterruptedException {
		WebElement normalalert=driver.findElement(By.xpath("//a[@href='#OKTab']"));
		normalalert.click();
		driver.findElement(By.xpath("//button[@onclick='alertbox()']")).click();
	
		Alert AcceptAlert=driver.switchTo().alert();
		AcceptAlert.accept();
		WaitTemp();
	}
	
	@Test(priority=2)
	public void AcceptOrDismissAlert() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='#CancelTab']")).click();
		driver.findElement(By.xpath("//button[@onclick='confirmbox()']")).click();
		
		Alert AcceptDismissAlert=driver.switchTo().alert();
		AcceptDismissAlert.dismiss();
		String Text=driver.findElement(By.xpath("//div[@id='CancelTab']//p[@id='demo']")).getText();
		Assert.assertEquals(Text,"You Pressed Cancel");
		WaitTemp();
		
	}
	
	@Test(priority=4)
	public void PromptAlert() {
		
		driver.findElement(By.xpath("//a[@href='#Textbox']")).click();
		driver.findElement(By.xpath("//button[@onclick='promptbox()']")).click();
		Alert promptAlert=driver.switchTo().alert();
		
		promptAlert.sendKeys("Nishant");
		promptAlert.accept();
		
		String Text=driver.findElement(By.xpath("//div[@id='Textbox']//p")).getText();
		System.out.println(Text);
		
	}
	
	
	
	@AfterMethod
	public void CloseBrowser() {
		driver.quit();
		
	}
	
	private void WaitTemp() throws InterruptedException {
		Thread.sleep(5000);
	}
}
