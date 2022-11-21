package org.demo.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewConfigurer;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.demo.constants.FrameWorkConstants;
import org.demo.enums.CategoryType;
import org.demo.exceptions.FrameWorkExceptions;
import org.demo.constants.FrameWorkConstants;
import org.demo.enums.CategoryType;
import org.demo.exceptions.FrameWorkExceptions;
import org.demo.reports.ExtentManager;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/**
 * Perform initialization and termination of {@link com.aventstack.extentreports.ExtentReports}
 * After creating an instance for {@link com.aventstack.extentreports.ExtentTest}, it is delegated to ThreadLocal 
 * variable for providing thread safety.
 *
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.listeners.ListenerClass
 * @see org.demo.annotations.FrameWorkAnnotations
 */
public final class ExtentReport {

	/**
	 * Private constructor to avoid external instantiation
	 */
    private ExtentReport(){}
    private static ExtentReports extentReports;

    /**
	 * Set the initial configuration for the Extent Reports and decides the report generation path.
     * Nov , 2022
     * @author Pon Vignesh
	 * @param classname parameterized from the listener class
	 */
    public static void initReports(String classname)  {

        if(Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            FrameWorkConstants.setReportClassName(classname);
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameWorkConstants.getReportFilepath()).viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST })
                    .apply();;
            extentReports.attachReporter(extentSparkReporter);
            extentSparkReporter.config().setTheme(Theme.STANDARD);

            extentSparkReporter.config().setDocumentTitle(classname+"_Automation Report");
            extentSparkReporter.config().setReportName("Pon Vignesh");
        }
    }

	/**
	 * Flushing the reports ensures extent logs are reflected properly. 
	 * Opens the report in the default desktop browser.
	 * Sets the ThreadLocal variable to default value
     * Nov , 2022
     * @author Pon Vignesh
	 */
    public static void flushReports() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
            ExtentManager.unExtentTest();
            try {
                Desktop.getDesktop().browse(new File(FrameWorkConstants.getReportFilepath()).toURI());
            }
            catch (FileNotFoundException e){
                throw new FrameWorkExceptions("Html report file not found, please check the report directory !");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
	 * Creates a test node in the extent report. Delegates to {@link ExtentManager} for providing thread safety
     * Nov , 2022
     * @author Pon Vignesh
	 * @param testcasename Test Name that needs to be reflected in the report
	 */
    public static void createTest(String testcasename){
        ExtentManager.setExtentTest(extentReports.createTest(testcasename));

    }
    
    /**
	 * Logs the authors details in the authors view in the extent report.
	 * Gives an clear idea of Authors Vs Percentage success metrics
     * Nov , 2022
     * @author Pon Vignesh
	 * @param authors Authors who created a particular test case
	 */
    public static void addAuthors(String[] authors){
        for (String author:authors
             ) {
            ExtentManager.getExtentTest().assignAuthor(author);
        }
    }

    /**
	 * Adds the category a particular test case belongs to.
	 * Gives an clear idea of Group Vs Percentage success metrics.
     * Nov , 2022
     * @author Pon Vignesh
	 * @param categories category a particular test case belongs to.
	 */
    public static void addCategories(String testName , CategoryType[] categories){
        for (CategoryType category: categories
             ) {

            ExtentManager.getExtentTest().assignCategory(testName +" : "+category.toString());
        }

    }

}
