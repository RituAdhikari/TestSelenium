package com.streetlink.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * This class is for generates HTML reports with the help of Extent class
 *
 */
public class ExtentManager{
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Streetlink Testing Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Ritu");
        return extentReports;
    }
}
