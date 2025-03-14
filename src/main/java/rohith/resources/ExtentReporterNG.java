package rohith.resources;

import java.io.File;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG {
	@Test
	public static ExtentReports getReport()
	{
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
        File reportDir = new File(System.getProperty("user.dir") + "\\reports");
        
        // Create the reports directory if it does not exist
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Ecommerce web testing");
        reporter.config().setDocumentTitle("Automation Testing");
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Rohith");
        
        return extent;
		
		
		
		
 

		
	}

}
