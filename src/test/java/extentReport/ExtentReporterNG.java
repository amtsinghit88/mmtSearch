package extentReport;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.testng.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;

import static com.aventstack.extentreports.markuputils.ExtentColor.BLUE;
public class ExtentReporterNG  implements  ITestListener{

	private Logger logger= LoggerFactory.getLogger(this.getClass().getName());
	private static ExtentReports extent = ExtentReportCreation.createInstance();
	private static ExtentTest parentExtentTest;
	private static ExtentTest childTest;

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();String dataParam=null;

	@Override
	public synchronized void onStart(ITestContext result) {
		parentExtentTest =extent.createTest(result.getName()).assignCategory(result.getSuite().getName());
//        test.set(parentExtentTest);
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		logger.info("Extent report location is {}",System.getProperty("user.dir")+ "/test-output/Extent-Report/ExtentReport.html");
		logger.info("Extent report location is {}",System.getProperty("user.dir")+ "/Reporting/Extent-Report/ExtentReport.html");
		logger.info("Extent report location is {}",ExtentReportCreation.createDynamicFiles());
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		dataParam=ExtentReportCreation.handleDynamicMethodName(result);
		childTest=parentExtentTest.createNode("TestName:"+methodName+"_"+dataParam);
		test.set(childTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String suiteName=result.getTestContext().getSuite().getName();
		logger.info((methodName + " passed!"));
		dataParam=ExtentReportCreation.handleDynamicMethodName(result);
		parentExtentTest.log(Status.PASS,MarkupHelper.createLabel("Method:"+methodName+"_"+dataParam+"_"+"Passed",ExtentColor.GREEN));
//        childTest.log(Status.PASS, MarkupHelper.createLabel("Method:"+methodName+"_"+"Passed",ExtentColor.GREEN));
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String suiteName=result.getTestContext().getSuite().getName();
		logger.error((methodName + " failed!"));
		dataParam=ExtentReportCreation.handleDynamicMethodName(result);
		parentExtentTest.log(Status.FAIL,MarkupHelper.createLabel("Method:"+methodName+"_"+dataParam+"_"+"Failed",ExtentColor.RED));
//        childTest.log(Status.FAIL, MarkupHelper.createLabel("Method:"+methodName+"_"+"Failed",ExtentColor.RED));
		test.get().fail(result.getThrowable());

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String suiteName=result.getTestContext().getSuite().getName();
		logger.error((methodName + " skipped!"));
		parentExtentTest.log(Status.SKIP,MarkupHelper.createLabel("Method:"+methodName+"_"+"Skipped",ExtentColor.BLUE));
		childTest.log(Status.SKIP, MarkupHelper.createLabel("Method:"+methodName+"_"+"Skipped", BLUE));
//        test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		logger.error(("onTestFailedButWithinSuccessPercentage for " + "Method:"+methodName));
	}
}
