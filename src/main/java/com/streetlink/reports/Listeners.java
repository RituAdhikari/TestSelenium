package com.streetlink.reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.streetlink.actiondriver.WebActionsHelper;
import com.aventstack.extentreports.Status;

/**
 * This class is for listeners implemention for Report 
 * @author rituadhikari
 *
 */
public class Listeners  implements ITestListener {

	WebActionsHelper action= new WebActionsHelper();
	
	
	public void onTestStart(ITestResult result){
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		Reporter.log(getmethodename(result)+": test case started");
		System.out.println("reports test on start");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
			Reporter.log(getmethodename(result)+": test case passed");
			System.out.println("reports test on pass");
		}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
			Reporter.log("The name of the testcase skipped is: "+getmethodename(result));
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");

	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
	}
	
	private static String getmethodename(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
}
