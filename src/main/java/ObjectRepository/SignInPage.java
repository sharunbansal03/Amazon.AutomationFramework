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
public class SignInPage {

	/**
	 * Identifying the web elements
	 */
	@FindBy(id = "ap_email")
	private WebElement emailOrMobileTxtField;

	@FindBy(id = "continue")
	private WebElement continueBtn;

	@FindBy(id = "ap_password")
	private WebElement passwordTxtField;

	@FindBy(id = "signInSubmit")
	private WebElement signInBtn;

	/**
	 * Constructor - intializing the objects
	 * 
	 * @param driver
	 */
	public SignInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Getters to utilize web elements
	 * 
	 * @return
	 */
	public WebElement getEmailOrMobileTxtField() {
		return emailOrMobileTxtField;
	}

	public WebElement getContinueBtn() {
		return continueBtn;
	}

	public WebElement getPasswordTxtField() {
		return passwordTxtField;
	}

	public WebElement getSignInBtn() {
		return signInBtn;
	}

	/**
	 * Business libraries - Generic methods
	 */

	public void signInToTheApplication(String email, String password) {
		emailOrMobileTxtField.sendKeys(email);
		continueBtn.click();
		passwordTxtField.sendKeys(password);
		signInBtn.click();
		Reporter.log("[STEP]: Signed into the application", true);
	}

}
