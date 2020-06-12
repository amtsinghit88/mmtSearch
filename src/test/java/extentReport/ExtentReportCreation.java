package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportCreation {
	private static ExtentReports extent;
	private static String reportFileName = createDynamicFiles();

	/*
	To create Extent Files as per date, inorder to track older Reports
	 */
	public static String createDynamicFiles() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		File dir = new File(System.getProperty("user.dir") + "/Reporting/Extent-Report/");
		boolean successful = dir.mkdirs();
		if (successful)
			System.out.println("directories were created successfully");
		else
			System.out.println("failed trying to create the directories");
		String fileName = System.getProperty("user.dir") + "/Reporting/Extent-Report/" + "ExtentReport_" + todaysDate + ".html";
		try {
			File file = new File(fileName);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 This Method is used to crate an instance of extent Report
	 */
	public static ExtentReports createInstance() {
		String fileName = reportFileName;
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Custom Report");
		htmlReporter.config().setChartVisibilityOnOpen(false);
		htmlReporter.config().setExtentXUrl("Hotels Automation Test Execution Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
		htmlReporter.config().setReportName("Hotels_Automation_Test_Execution_Report");
		String tests="$( \"div.card-panel\" ).find( \"#charts-row > div\" ).find('.left').eq(0).text('Flow Count');";
		String steps="$( \"div.card-panel\" ).find( \"#charts-row > div\" ).find('.left').eq(1).text('Test Case (or) Steps Count');";
		String category="$(\"#dashboard-view > div\").find(\".dashboard-categories > span\").text('Automated Test Flow Count Chart');";
		htmlReporter.config().setCSS("#charts-row > div:nth-child(1) >div");
		String timeTaken="$(document).ready(function(){var String =$(\"#dashboard-view > div > div > div:nth-child(5) > div > div,panel-lead\").text(); " +
				"var stepValue =$(\"#dashboard-view > div \").find('.row').eq(1).find('.col').eq(1).find('div').find('.panel-lead').text();"+
				"var testValue = $(\"#dashboard-view > div \").find('.row').eq(1).find('.col').eq(0).find('div').find('.panel-lead').text();"+
				"var textDynamicValue = $( \"div.card-panel\" ).find( \"#charts-row > div\" ).find('.text-small > span').eq(0).find('span').text();"+
				"$( \"div.card-panel\" ).find( \"#charts-row > div\" ).find('.text-small > span').eq(0).html(\"<span class='strong'>\"+textDynamicValue +\"</span> flows  passed\");"+
				"$(\"#dashboard-view > div\").find('.row').eq(1).find('.col').eq(0).remove();"+
				"$(\"#dashboard-view > div \").find('.row').eq(1).find('.col').eq(0).find('div').text('Test Case Count').append(\"<div class='panel-lead'>\"+stepValue+\"</div>\");"+
				"});";
		htmlReporter.config().setJS("document.querySelector('a[view=test-view]').parentElement.classList.remove('active');\n" +
				"document.querySelector('a[view=dashboard-view]').parentElement.classList.add('active');\n" +
				"document.querySelector('a[view=dashboard-view]').click();"+
				timeTaken+
				tests+
				steps+
				category);
		extent = new ExtentReports();
		htmlReporter.config().setCSS("#charts-row > div:nth-child(1) >div { display: none; }");
		extent.setReportUsesManualConfiguration(true);
		extent.attachReporter(htmlReporter);
		return extent;
	}

	public static String handleDynamicMethodName(ITestResult result)
	{

		StringBuilder returnValue = new StringBuilder();

		for (Object p : result.getParameters()) {
			returnValue.append(p);
		}
		return returnValue.toString();
	}
}
