package ObjectRepository;

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
public class SearchResultsPage extends WebDriverUtility {

	/**
	 * Identifying the web elements
	 * 
	 */
	@FindBy(id = "nav-signin-tooltip")
	private WebElement SignInToolTipBtn;

	@FindBy(xpath = "//span[@data-component-type='s-result-info-bar']")
	private WebElement ResultInfoBarText;

	@FindBy(xpath = "//span[@data-component-type='s-search-results']")
	private WebElement SearchResultsSection;

	@FindBy(xpath = "//span[@data-component-type='s-product-image'][1]")
	private WebElement firstProductLnk;

	@FindBy(xpath = "//div[@data-component-type='s-search-result']//img")
	private WebElement FirstResultImage;

	@FindBy(xpath = "//div[@data-component-type='s-search-result']//h2")
	private WebElement FirstResultTitle;

	/**
	 * Constructor - initializing the web elements/objects
	 * 
	 * @param driver
	 */
	public SearchResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Getters method to web elements
	 */

	public WebElement getSignInToolTipBtn() {
		return SignInToolTipBtn;
	}

	public WebElement getResultInfoBarText() {
		return ResultInfoBarText;
	}

	public WebElement getSearchResultsSection() {
		return SearchResultsSection;
	}

	public WebElement getFirstResultImage() {
		return FirstResultImage;
	}

	public WebElement getFirstResultTitle() {
		return FirstResultTitle;
	}

	/**
	 * Business Libraries - Generic methods
	 */
	
	/**
	 * This method will click on first product in search results and return its title
	 * @return
	 */
	public String clickFirstProductAndReturnTitle() {
		String firstResultTitle = FirstResultTitle.getText();
		FirstResultImage.click();
		Reporter.log("[Step]: Clicked on product titled:" + firstResultTitle, true);
		return firstResultTitle;
	}
}
