package com.reqres.utils;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/ExtentReport.html");
		htmlReporter.config().setDocumentTitle("Rest-Assured Automation testing");
		htmlReporter.config().setReportName("Reqres API Testing");
		htmlReporter.config().setTheme(Theme.DARK);

		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "Local Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Thasim");
	}

	public void onTestSuccess(ITestResult result) {

		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test case passed :"+ result.getName());
		
		ITestNGMethod method = result.getMethod();
		System.out.println("Success percentage for test '" + method.getMethodName() + "': " + method.getSuccessPercentage());


	}

	public void onTestFailure(ITestResult result) {

		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case failed :"+ result.getName());
		test.log(Status.FAIL, "Test case failed :"+ result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {

		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case Skipped :"+ result.getName());
	}


	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}
}
