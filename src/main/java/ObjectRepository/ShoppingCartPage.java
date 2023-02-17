package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/**
 * This class consists of Web Elements and business libraries related to Sign In
 * Page
 * 
 * @author sharu
 *
 */
public class ShoppingCartPage {

	/**
	 * Identifying the web elements
	 */
	@FindBy(xpath = "//form[@id='activeCartViewForm']//span[@class='a-truncate-cut']")
	private WebElement CartProductTitle;

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement DeleteItemFromCartLnk;

	/**
	 * Constructor - intializing the objects
	 * 
	 * @param driver
	 */
	public ShoppingCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Getters to utilize web elements
	 * 
	 * @return
	 */
	public WebElement getCartProductTitle() {
		return CartProductTitle;
	}

	public WebElement getDeleteItemFromCartLnk() {
		return DeleteItemFromCartLnk;
	}

	/**
	 * Business libraries - Generic methods
	 * 
	 * @throws InterruptedException
	 */

	/**
	 * This method will click on 'Delete' item for first item in cart
	 */
	public void clickDeleteItemFromCart() {
		DeleteItemFromCartLnk.click();
		Reporter.log("[Step]: Clicked on 'Delete' for first item in cart");
	}
}
