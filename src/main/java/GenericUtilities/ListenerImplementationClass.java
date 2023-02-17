package GenericUtilities;

import java.io.IOException;

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


	public void onTestStart(ITestResult result) {
		System.out.println("inside on test start");
		String methodName = result.getMethod().getMethodName();
		ExtentTestManagerUtility.startTest(methodName);
		ExtentTestManagerUtility.getTest().log(Status.INFO, "Test Execution Started: " + methodName);
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTestManagerUtility.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		JavaUtility jUtils = new JavaUtility();
		String methodName = result.getMethod().getMethodName();
		ExtentTestManagerUtility.getTest().log(Status.FAIL, "Test Script failed - " + methodName);
		ExtentTestManagerUtility.getTest().log(Status.FAIL, result.getThrowable());

		WebDriverUtility wUtils = new WebDriverUtility();
		try {
			String path = wUtils.takeScreenshot(BaseClass.sDriver,
					methodName + "_" + jUtils.getSystemDataAndTimeInFormat());
			ExtentTestManagerUtility.getTest().addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		JavaUtility jUtils = new JavaUtility();
		String methodName = result.getMethod().getMethodName();
		ExtentTestManagerUtility.getTest().log(Status.SKIP, methodName);
		ExtentTestManagerUtility.getTest().log(Status.SKIP, result.getThrowable());
		try {
			String path = new WebDriverUtility().takeScreenshot(BaseClass.sDriver,
					methodName + "_" + jUtils.getSystemDataAndTimeInFormat());
			ExtentTestManagerUtility.getTest().addScreenCaptureFromPath(path);
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
