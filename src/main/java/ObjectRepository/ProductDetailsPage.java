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
public class ProductDetailsPage extends WebDriverUtility {

	/**
	 * Identifying the web elements
	 * 
	 */

	@FindBy(id = "add-to-cart-button")
	private WebElement AddToCartButton;

	@FindBy(id = "attach-sidesheet-view-cart-button")
	private WebElement SideSheetCartBtn;

	/**
	 * Constructor - initializing the web elements/objects
	 * 
	 * @param driver
	 */
	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Getters method to web elements
	 */

	public WebElement getSideSheetCartBtn() {
		return SideSheetCartBtn;
	}

	public WebElement getAddToCartButton() {
		return AddToCartButton;
	}

	/**
	 * Business Libraries - Generic methods
	 */

	/**
	 * This method will click on 'Add to cart' button
	 */
	public void clickAddToCartBtn() {
		AddToCartButton.click();
		Reporter.log("[Step]: Clicked on 'Add to Cart' button", true);
	}
	
	/**
	 * This method will click on 'Cart' button from side-sheet view
	 */
	public void clickSideSheetCartBtn() {
		SideSheetCartBtn.click();
		Reporter.log("[Step]: Clicked on 'Cart' button", true);
	}

}
