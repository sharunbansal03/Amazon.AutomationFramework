package amazon.PlaceOrder.TestScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import ObjectRepository.HomePage;
import ObjectRepository.ProductDetailsPage;
import ObjectRepository.SearchResultsPage;
import ObjectRepository.ShoppingCartPage;

@Listeners(GenericUtilities.ListenerImplementationClass.class)
public class AddProductToCartTest extends BaseClass {

	@Test(groups = "SmokeSuite")
	public void addProductToCartTest() throws IOException, InterruptedException {
		// Step 1: Search product
		HomePage hp = new HomePage(driver);
		hp.searchProduct(eUtils.readDataFromExcelSheet("PlaceOrder", 1, 2));

		// Step 2: Select product
		SearchResultsPage srp = new SearchResultsPage(driver);
		String expectedProductTitle = srp.clickFirstProductAndReturnTitle();

		// Step 3: Switch to child window
		wUtils.switchToWindow(driver, expectedProductTitle);

		// Step 4: Click 'Add to cart'
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		pdp.clickAddToCartBtn();

		// Step 5: Click 'Cart'
		pdp.clickSideSheetCartBtn();

		// Step 6: Verify product present in cart
		ShoppingCartPage scp = new ShoppingCartPage(driver);
		String actualProductTitle = scp.getCartProductTitle().getText();

		Assert.assertEquals(actualProductTitle, expectedProductTitle,
				"[Assertion Failed]: Adding product to cart failed");
		Reporter.log("[Assertion Passed]: Add product to cart successful", true);
		
		scp.clickDeleteItemFromCart();
	}

}
