package Assesment.FlipKart;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	static ExtentReports report;
	
public static  ExtentReports extentReportConfig() {
	//ExtentReports ExtentSparkReporter
			String path= System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter= new ExtentSparkReporter(path);
			reporter.config().setReportName("FlipKart Assesment Report");
			reporter.config().setDocumentTitle(" FlipKart Test Results");
			
			 report=new ExtentReports();
			 report.attachReporter(reporter);
			 report.setSystemInfo("Tester", "Saranya");
			 
			 return report;
}
	
}