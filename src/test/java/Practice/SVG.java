package Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SVG {

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void svgtest() {
	WebDriver driver=new ChromeDriver();
	
	driver.get("https://www.worldometers.info/coronavirus/country/india/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	WebElement grap=driver.findElement(By.xpath("(//*[local-name()='svg' and @class='highcharts-root'])[2]"));
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView()", grap);
	
	List<WebElement> graphopt=driver.findElements(By.xpath("(//*[local-name()='svg' and @class='highcharts-root'])[2]//*[local-name()='path']"));
	System.out.println(graphopt.size());
	Actions action=new Actions(driver);
	for(WebElement p:graphopt) {
		action.moveToElement(p).perform();
	}
	
	}
}
