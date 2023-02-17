package GenericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ObjectRepository.HomePage;
import ObjectRepository.SignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains Basic configurations for test scripts
 * 
 * @author sharu
 *
 */
public class BaseClass {
	public PropertyFileUtility pUtils = new PropertyFileUtility();
	public JavaUtility jUtils = new JavaUtility();
	public ExcelFileUtility eUtils = new ExcelFileUtility();
	public WebDriverUtility wUtils = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver;

	/**
	 * This method will run before suite start and creates an extent report
	 */
	@BeforeSuite(groups = "SmokeSuite")
	public void bsConfig() {
		System.out.println("======= Suite execution started=============");
		ExtentManagerUtility.setUpExtentReport();
	}

	/**
	 * This method will flush content of suite execution tests to extent report
	 */
	@AfterSuite(groups = "SmokeSuite")
	public void asConfig() {
		System.out.println("======= Suite execution finished=============");
		//ExtentManagerUtility.endReport();
	}

	/**
	 * This method will execute before every class and launches browser +
	 * application
	 * 
	 * @throws IOException
	 */
	@BeforeClass(groups = "SmokeSuite")
	public void bcConfig() throws IOException {
		String BROWSER = pUtils.readDataFromPropertyFile("browser");
		String URL = pUtils.readDataFromPropertyFile("url");

		if (BROWSER.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else
			System.out.println("Invalid browser");

		wUtils.maximizeWindow(driver);
		wUtils.waitForPageToLoad(driver);
		driver.get(URL);
	}

	/**
	 * This method will execute before every test method and perform login action
	 * 
	 * @throws IOException
	 */
	@BeforeMethod(groups = "SmokeSuite")
	public void bmConfig() throws IOException {
		String email = pUtils.readDataFromPropertyFile("username");
		String password = pUtils.readDataFromPropertyFile("password");

		HomePage hp = new HomePage(driver);
		hp.hoverAccountListDrpDownAndClickSignInBtn(driver);
		SignInPage sp = new SignInPage(driver);
		sp.signInToTheApplication(email, password);
	}

	/**
	 * This method will execute after every test method and perform logout action
	 */
	@AfterMethod(groups = "SmokeSuite", alwaysRun = true)
	public void amConfig() {
		HomePage hp = new HomePage(driver);
		hp.logoutOfApplication(driver);
		ExtentManagerUtility.flushReport();
	}

	/**
	 * This method will close the driver/browser
	 */
	@AfterClass(groups = "SmokeSuite")
	public void acConfig() {
		driver.quit();
	}
}
