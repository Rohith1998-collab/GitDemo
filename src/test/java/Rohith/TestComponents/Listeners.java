package Rohith.TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rohith.resources.ExtentReporterNG;

public class Listeners implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	 @Override
	    public void onTestStart(ITestResult result) {
	        // Code to execute when a test starts
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // Code to execute when a test is successful
	    	extentTest.get().log(Status.PASS, "Test case Passed");
	    	//test.addScreenCaptureFromPath("C:\\Users\\Rohith\\my-app\\target", "pass.png");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // Code to execute when a test fails
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // Code to execute when a test is skipped
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Code to execute when a test fails but within success percentage
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // Code to execute when a test context starts
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // Code to execute when a test context finishes
	    	extent.flush();
	
}
}
