package GenericUtilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * This class will implement abstract methods of ITestListener Setup of Extent
 * Report and Capture screenshot on failure
 * 
 * @author sharu
 *
 */
public class ListenerImplementationClass extends ExtentManagerUtility implements ITestListener {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	

	public void onTestStart(ITestResult result) {
		System.out.println("inside on test start");
		String methodName = result.getMethod().getMethodName();
		ExtentTest test = report.createTest(methodName);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		
		extentTestMap.get((int) Thread.currentThread().getId()).log(Status.INFO, "Test Execution Started: " + methodName);
	}

	public void onTestSuccess(ITestResult result) {
		extentTestMap.get((int) Thread.currentThread().getId()).log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		JavaUtility jUtils = new JavaUtility();
		String methodName = result.getMethod().getMethodName();
		extentTestMap.get((int) Thread.currentThread().getId()).log(Status.FAIL, "Test Script failed - " + methodName);
		extentTestMap.get((int) Thread.currentThread().getId()).log(Status.FAIL, result.getThrowable());

		WebDriverUtility wUtils = new WebDriverUtility();
		try {
			String path = wUtils.takeScreenshot(BaseClass.sDriver,
					methodName + "_" + jUtils.getSystemDataAndTimeInFormat());
			extentTestMap.get((int) Thread.currentThread().getId()).addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		JavaUtility jUtils = new JavaUtility();
		String methodName = result.getMethod().getMethodName();
		extentTestMap.get((int) Thread.currentThread().getId()).log(Status.SKIP, methodName);
		extentTestMap.get((int) Thread.currentThread().getId()).log(Status.SKIP, result.getThrowable());
		try {
			String path = new WebDriverUtility().takeScreenshot(BaseClass.sDriver,
					methodName + "_" + jUtils.getSystemDataAndTimeInFormat());
			extentTestMap.get((int) Thread.currentThread().getId()).addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

}
