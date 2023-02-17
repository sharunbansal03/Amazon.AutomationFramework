package GenericUtilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManagerUtility {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	static ExtentReports extent = ExtentManagerUtility.setUpExtentReport();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}
}
