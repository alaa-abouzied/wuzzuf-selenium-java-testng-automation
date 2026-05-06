package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getInstance(){
        if(extent == null){
            sparkReporter = new ExtentSparkReporter("Reports/myReport.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName){
        test = getInstance().createTest(testName);
        return test;
    }

    public static void flushReport() throws IOException {
        extent.flush();
        Desktop.getDesktop().open(new File("Reports/myReport.html"));
    }


}
