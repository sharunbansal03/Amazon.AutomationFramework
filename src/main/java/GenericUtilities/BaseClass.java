package GenericUtilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.common.base.Strings;

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
		ExtentManagerUtility.flushReport();
	}

	/**
	 * This method will execute before every class and launches browser +
	 * application
	 * 
	 * @throws IOException
	 */

	@Parameters("browser")
	@BeforeClass(groups = "SmokeSuite")
	public void bcConfig(@Optional String browserFromSuite) throws IOException {
		String BROWSER = null;
		// If parameters provided from suite file
		if (browserFromSuite != null) {
			BROWSER = browserFromSuite;
		}
		// If Build with parameters from jenkins, browser = parameter value;
		else if (!Strings.isNullOrEmpty(System.getProperty("browser")))
			BROWSER = System.getProperty("browser");
		// Else pick from CommonData.properties file
		else
			BROWSER = pUtils.readDataFromPropertyFile("browser");

		if (BROWSER.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
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

		// If Build with parameters from jenkins, url = parameter value; else pick url
		// from property file
		String URL = null;
		if (!Strings.isNullOrEmpty(System.getProperty("url")))
			URL = System.getProperty("url");
		else
			URL = pUtils.readDataFromPropertyFile("url");
		Reporter.log("url: " + URL, true);
		driver.get(URL);
	}

	/**
	 * This method will execute before every test method and perform login action
	 * 
	 * @throws IOException
	 */
	@BeforeMethod(groups = "SmokeSuite")
	public void bmConfig(Method m) throws IOException {
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
	}

	/**
	 * This method will close the driver/browser
	 */
	@AfterClass(groups = "SmokeSuite")
	public void acConfig() {
		driver.quit();
	}
}
