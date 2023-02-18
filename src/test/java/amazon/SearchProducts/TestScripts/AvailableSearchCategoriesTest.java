package amazon.SearchProducts.TestScripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import ObjectRepository.HomePage;

public class AvailableSearchCategoriesTest extends BaseClass {

	@Test
	public void availableCategoriesTest() throws IOException {
		List<String> expectedCategories = Arrays
				.asList(eUtils.readDataFromExcelSheet("SearchProducts", 1, 2).split(";"));
		HomePage hp = new HomePage(driver);
		List<String> actualCategories = hp.getAllSearchCategories();

		Assert.assertEquals(actualCategories, expectedCategories,
				"[Assertion Failed]: Search categories are not correct");
		Reporter.log("[Assertion Passed]: Search categories are correct", true);
	}
}
