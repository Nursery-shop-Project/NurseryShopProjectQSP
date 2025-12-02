package com.nurseryshopapp.genericlibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dwsapp.pagerepository.AccessoriesComputerspage;
import org.dwsapp.pagerepository.BookPage;
import org.dwsapp.pagerepository.Camerapage;
import org.dwsapp.pagerepository.CellPhonesPage;
import org.dwsapp.pagerepository.DeskTopComputersPage;
import org.dwsapp.pagerepository.LoginPage;
import org.dwsapp.pagerepository.NoteBooksComputerPage;
import org.dwsapp.pagerepository.WelcomePage;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseConfig extends WebDriverLibrary {

	@BeforeTest
	public void reportSetup() {

		// Give Current Time Stamp
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String customtime = sdf.format(new Date());

		// create spark report
		spark = new ExtentSparkReporter("./AdvanceReports/report"+customtime+".html");

		// configure the SparkReport information
		spark.config().setDocumentTitle("Regression Testing for the demoWebShop ");
		spark.config().setReportName("Regressionsuite");
		spark.config().setTheme(Theme.STANDARD);

		// create the extentReport
		report = new ExtentReports();

		// Attach the Sparkreport and extentReport
		report.attachReporter(spark);

		// configure the system information in extentReport
		report.setSystemInfo("DeviceName:", "assi");

		report.setSystemInfo("OperatingSystem:", "Windows11");

		report.setSystemInfo("Browser:", "Chrome");

		report.setSystemInfo("BrowserVersion:", "chrome-142.0.7444.163");
	}

	@AfterTest
	public void reportTerminate() {

		// flush the report info
		report.flush();
	}

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void browserSetup(String browser, String url) {

		openBrowser(browser);

		maximizeBrowser();

		waitStatement();

		navigateToApp(url);

		Reporter.log("Browser setup success", true);

	}

	@BeforeMethod
	public void login() {

		plib = new PropertiesLibrary();

		waitStatement();

		wpobj = new WelcomePage(driver);
		clickOnElement(wpobj.getLoginlink());

		lpobj = new LoginPage(driver);

		enterDataOnElement(lpobj.getemailTextfield(), plib.readData("email"));
		enterDataOnElement(lpobj.getpasswordTextField(), plib.readData("password"));
		clickOnElement(lpobj.getloginbutton());

		Reporter.log("Login success", true);

	}

	@AfterMethod
	public void logout() {

		wpobj = new WelcomePage(driver);
		clickOnElement(wpobj.getLogoutlink());
		Reporter.log("Log out success", true);

	}

	@AfterClass
	public void browserTeminate() {
		closeBrowser();
		Reporter.log("Browser terminate success", true);
	}

}