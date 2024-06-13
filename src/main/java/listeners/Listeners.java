package listeners;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
	ExtentReports extentReport = ExtentReporter.getExtentReport()
;
	WebDriver driver = null;
	ExtentTest extentTest ;
	

	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		 extentTest = extentReport.createTest(testName+ " test execution begins ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();

		extentTest.log(Status.PASS, testName+ "is passed" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName=result.getName();
		extentTest.fail(result.getThrowable());

		String testMethodName = result.getName();//getting test name 
		
		
			try {
				driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//driver of the failing test
		
		
		try {
			takeScreenshot(testMethodName,driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush(); //if not writtten nothing will happen
		
	}
	
	

}
