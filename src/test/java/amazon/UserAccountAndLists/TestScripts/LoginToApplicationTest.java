package amazon.UserAccountAndLists.TestScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import ObjectRepository.HomePage;

@Listeners(GenericUtilities.ListenerImplementationClass.class)

public class LoginToApplicationTest extends BaseClass {

	@Test
	public void loginIntoAppTest() throws IOException {
		String expectedUserName = eUtils.readDataFromExcelSheet("UserAccountAndList", 1, 2);
		HomePage hp = new HomePage(driver);
		String actualUserName = hp.getUsernameText().getText();
		Assert.assertTrue(actualUserName.equals(expectedUserName), "[ASSERTION FAILED]: Sign in unsuccessful");
		Reporter.log("[ASSERTION PASSED]: SIGN IN SUCCESSFUL", true);
		System.out.println("added to check git conflict");
	}
}
