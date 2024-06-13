package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		
		String extentReportPath = System.getProperty("user.dir")+"\\reports\\extentreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
		reporter.config().setReportName("Flipkart website test results");
		reporter.config().setDocumentTitle("Test Results");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Login","Through otp");
		extentReport.setSystemInfo("Cart"," Can work without login");
		extentReport.setSystemInfo("Checkout","Requires login");


		extentReport.setSystemInfo("Tested By","Ishita Sankrityayan");
		
		return extentReport;
		
	}

}
