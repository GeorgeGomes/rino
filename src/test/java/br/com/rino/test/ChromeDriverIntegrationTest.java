package br.com.rino.test;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverIntegrationTest {

	@Test
	public void testGoogleSearch() throws InterruptedException {
	  // Optional, if not specified, WebDriver will search your path for chromedriver.
	  
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/Users/george.gomes/AppData/Local/Google/Chrome/Application/chrome.exe");
		options.addArguments("--user-data-dir=userdir");
		System.setProperty("webdriver.chrome.driver", "/rino/chromedriver.exe"); 
		
		WebDriver driver = new ChromeDriver(options);
		
		//Dimension windowMinSize = new Dimension(0,0);
		//driver.manage().window().setSize(windowMinSize);
		
		driver.manage().deleteAllCookies();
		
	  //driver.get("http://www.google.com/xhtml");
	  //driver.manage().deleteAllCookies();
	  //Thread.sleep(5000);  // Let the user actually see something!
	  //WebElement searchBox = driver.findElement(By.name("q"));
	  //searchBox.sendKeys("ChromeDriver");
	  //searchBox.submit();
	  //Thread.sleep(5000);  // Let the user actually see something!
		driver.quit();
	}

}
