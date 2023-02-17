package ObjectRepository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import GenericUtilities.WebDriverUtility;

/**
 * This class defines and initializes objects/elements for Home page and
 * contains Business libraries
 * 
 * @author sharu
 *
 */
public class HomePage extends WebDriverUtility {

	/**
	 * Identifying the web elements
	 * 
	 */

	@FindBy(id = "nav-link-accountList-nav-line-1")
	private WebElement usernameText;

	@FindBy(id = "nav-link-accountList")
	private WebElement AccountAndListBtn;

	@FindBy(id = "nav-item-signout")
	private WebElement SignOutLink;

	@FindBy(xpath = "//select[@id='searchDropdownBox']/option")
	private List<WebElement> SearchCategoriesOptions;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchTextField;

	@FindBy(xpath="//a[@data-nav-role='signin']")
	private WebElement SignInBtn;

	/**
	 * Constructor - initializing the web elements/objects
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Getters method to web elements
	 */

	public WebElement getSignInBtn() {
		return SignInBtn;
	}
	
	public WebElement getUsernameText() {
		return usernameText;
	}

	public WebElement getAccountAndListBtn() {
		return AccountAndListBtn;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}

	public List<WebElement> getSearchCategoriesOptions() {
		return SearchCategoriesOptions;
	}

	public WebElement getsearchTextField() {
		return searchTextField;
	}

	/**
	 * Business Libraries - Generic methods
	 */

	/**
	 * This method will click on hoven on "Account &Lists" and Click Sign In button
	 * the application
	 */
	public void hoverAccountListDrpDownAndClickSignInBtn(WebDriver driver) {
		mouseHoverAction(driver, searchTextField);
		waitForElementToBeClickable(driver, SignInBtn);
		SignInBtn.click();
		Reporter.log("[STEP]: Clicked on Sign In Button", true);
	}

	/**
	 * This method will log out from the application
	 * 
	 * @param driver
	 */
	public void logoutOfApplication(WebDriver driver) {
		mouseHoverAction(driver, AccountAndListBtn);
		SignOutLink.click();
		Reporter.log("[STEP]: Logged out of the application", true);
	}

	/**
	 * This method returns list of available search categories in dropdown
	 * 
	 * @return
	 */
	public List<String> getAllSearchCategories() {
		List<String> allOptionsText = new ArrayList<String>();
		for (WebElement option : SearchCategoriesOptions) {
			allOptionsText.add(option.getText());
		}
		return allOptionsText;
	}

	/**
	 * This method will enter search token in text field and press enter
	 * 
	 * @param searchToken
	 */
	public void searchProduct(String searchToken) {
		searchTextField.sendKeys(searchToken);
		searchTextField.sendKeys(Keys.ENTER);
		Reporter.log("[Step]: Search for item: " + searchToken, true);
	}

}
