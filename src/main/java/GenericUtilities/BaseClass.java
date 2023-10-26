package GenericUtilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
		String SERVER = null;

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

		// if server=remote or local specified from jenkins pick that , else pick from CommonData.properties file
		if(System.getProperty("server")!=null) {
			SERVER = System.getProperty("server");
		}else
			SERVER = pUtils.readDataFromPropertyFile("server");
		
		if (SERVER.equalsIgnoreCase("remote")) {
			System.out.println("************ SETTING UP REMOTE BROWSER ************");
			setUpRemoteWebDriver(BROWSER);
		} else if (SERVER.equalsIgnoreCase("local")) {
			System.out.println("************ SETTING UP LOCAL BROWSER ************");
			setUpLocalWebDriver(BROWSER);
		}

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
		// LAUNCH APPLICATION
		driver.get(URL);
	}

	public void setUpLocalWebDriver(String BROWSER) {
		System.out.println("inside method");
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else if (BROWSER.equalsIgnoreCase("MicrosoftEdge")) {
			driver = new EdgeDriver();
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else if (BROWSER.equalsIgnoreCase("internet explorer")) {
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.attachToEdgeChrome();
			driver = new InternetExplorerDriver(ieOptions);
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else
			System.out.println("Invalid browser");
	}

	public void setUpRemoteWebDriver(String BROWSER) throws MalformedURLException {
		URL hubURL = new URL("http://192.168.1.29:4444/");
		DesiredCapabilities cap = new DesiredCapabilities();

		if (BROWSER.equalsIgnoreCase("Chrome")) {
			cap.setBrowserName(BROWSER);
			cap.setPlatform(Platform.WINDOWS);
			ChromeOptions options = new ChromeOptions();
			options.merge(cap);
			driver = new RemoteWebDriver(hubURL, options);
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			cap.setBrowserName(BROWSER);
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(hubURL, cap);
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} 
		else if (BROWSER.equalsIgnoreCase("MicrosoftEdge")) {
			cap.setBrowserName(BROWSER);
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(hubURL, cap);
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} 
		
		else if (BROWSER.equalsIgnoreCase("internet explorer")) {
			cap.setBrowserName(BROWSER);
			cap.setPlatform(Platform.WINDOWS);
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.attachToEdgeChrome();
			ieOptions.merge(cap);
			driver = new RemoteWebDriver(hubURL, ieOptions);
			sDriver = driver;
			Reporter.log("********Launched " + BROWSER + " browser ********", true);
		} else
			System.out.println("Invalid browser");
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
