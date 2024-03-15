package com.book.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenerator implements ITestListener {

    ExtentReports extentReport;
    Object object;
    ExtentTest extentTest;

    public ReportGenerator() {
    	
    	ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html");

        extentReport = new ExtentReports();
        object = extentReport;
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        ((ExtentReports) object).attachReporter(sparkReporter);
    }

    public void testName(String testName) {
        extentTest = ((ExtentReports) object).createTest(testName);
    }

    public void logsInfo(String log) {
        extentTest.log(Status.INFO, log);
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport = generateExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + " started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        String destinationScreenshotPath = TestUtils.captureScreenshot(driver, result.getName());

        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, result.getName() + " got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, result.getName() + " got skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html";
        File extentReportFile = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExtentReports generateExtentReports() {
        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Hybrid Test Automation Results Report");
        sparkReporter.config().setDocumentTitle("Hybrid Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        Properties configProp = new Properties();

        try {
            FileInputStream fisConfigProp = new FileInputStream("./src/main/java/com/book/config/config.properties");
            configProp.load(fisConfigProp);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
        extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
        extentReport.setSystemInfo("Password", configProp.getProperty("password"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}
