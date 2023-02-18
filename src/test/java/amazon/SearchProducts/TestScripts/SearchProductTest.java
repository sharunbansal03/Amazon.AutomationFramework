package amazon.SearchProducts.TestScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import ObjectRepository.HomePage;
import ObjectRepository.SearchResultsPage;

public class SearchProductTest extends BaseClass {

	@Test(groups = "SmokeSuite")
	public void searchProductTest() throws IOException {
		String searchItem = eUtils.readDataFromExcelSheet("SearchProducts", 4, 2);
		String expectedResultInfo = " results for \"" + searchItem + "\"";
		HomePage hp = new HomePage(driver);
		hp.searchProduct(searchItem);

		SearchResultsPage srp = new SearchResultsPage(driver);
		String actualResultInfo = srp.getResultInfoBarText().getText();
		Assert.assertTrue(actualResultInfo.contains(expectedResultInfo),
				"[Assertion Failed]: Search product test failed");
		Reporter.log("[Assertion Passed]: Search product test passed", true);

		Assert.assertTrue(srp.getSearchResultsSection().isDisplayed(),
				"[Assertion Failed]: Search results are not loaded");
		Reporter.log("[Assertion Passed]: Search results displayed", true);
	}
}
