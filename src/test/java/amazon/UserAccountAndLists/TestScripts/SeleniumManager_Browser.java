package amazon.UserAccountAndLists.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.Test;

public class SeleniumManager_Browser {
	@Test
	public void launchBrowser() {
		// Edge in IE mode
		InternetExplorerOptions ieoptions = new InternetExplorerOptions();
		ieoptions.attachToEdgeChrome();
		WebDriver IEDriver = new InternetExplorerDriver(ieoptions);
		IEDriver.manage().window().maximize();
		IEDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		IEDriver.get("https://www.selenium.dev/documentation/webdriver/browsers/internet_explorer/");
		System.out.println(IEDriver.getTitle());
		IEDriver.findElement(By.xpath("//a[@href='/documentation/webdriver/']")).click();

		// MS Edge
		WebDriver driver2 = new EdgeDriver();
		driver2.manage().window().maximize();
		driver2.get("https://www.selenium.dev/documentation/webdriver/browsers/internet_explorer/");

		System.out.println(driver2.getTitle());
		driver2.findElement(By.cssSelector(".breadcrumb a[href='/documentation/webdriver/']")).click();

		// chrome
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.selenium.dev/documentation/webdriver/browsers/internet_explorer/");
		System.out.println(driver.getTitle());

		// firefox
		WebDriver driver1 = new FirefoxDriver();
		driver1.get("https://www.selenium.dev/documentation/webdriver/browsers/internet_explorer/");

		System.out.println(driver1.getTitle());

	}

}
