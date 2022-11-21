package org.demo.listeners;

import org.demo.annotations.FrameWorkAnnotations;
import org.demo.reports.ExtentLogger;
import org.demo.reports.ExtentReport;
import org.demo.utils.ELKRealTimeDashBoardUtils;
import org.testng.*;

import java.util.Arrays;
/**
 * Implements {@link org.testng.ITestListener} and {@link org.testng.ISuiteListener} to leverage the abstract methods
 * Mostly used to help in extent report generation
 * 
 * <pre>Please make sure to add the listener details in the testng.xml file</pre>
 * 
 * @author Pon Vignesh
 * Nov, 2022
 * @version 1.0
 * @since 1.0
 */
public class ListenerClass implements ISuiteListener, ITestListener, IAnnotationTransformer,IClassListener {

	/**
	 * Initialise the reports with the file name
	 * @see org.demo.reports.ExtentReport
	 */
    @Override
    public void onStart(ISuite iSuite) {
        ExtentReport.initReports(iSuite.getXmlSuite().getName());
    }
    /**
	 * Terminate the reports
	 * @see com.gcit.reports.ExtentReport
	 */
    @Override
    public void onFinish(ISuite iSuite) {

        ExtentReport.flushReports();

    }

    /**
	 * Starts a test node for each testng test
	 * @see org.demo.reports.ExtentReport
	 * @see org.demo.annotations.FrameWorkAnnotations
	 */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getDescription());
        ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameWorkAnnotations.class).testAuthor());
        ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getName(), result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameWorkAnnotations.class).catagory());
        //System.out.println(result.getMethod().getConstructorOrMethod().getMethod().getName());
    }

    /**
	 * <p>Marks the test as pass and logs it in the report
	 * send the script status to ELK live dashBoard</p><br>
	 * @see org.demo.reports.ExtentLogger
	 * @see org.demo.utils.ELKRealTimeDashBoardUtils
	 */
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExtentLogger.pass(result.getMethod().getMethodName() +" is passed !", true);
            ELKRealTimeDashBoardUtils.sendStatisticsELK(result.getMethod().getMethodName(), "Pass");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * <p>Marks the test as pass and logs it in the report
	 * send the script status to ELK live dashBoard</p><br>
	 * @see org.demo.reports.ExtentLogger
	 * @see org.demo.utils.ELKRealTimeDashBoardUtils
	 */
    @Override
    public void onTestFailure(ITestResult result) {
        //attach screenshot
        try {
            ExtentLogger.fail(result.getMethod().getMethodName() +" is failed !", true);
            ELKRealTimeDashBoardUtils.sendStatisticsELK(result.getMethod().getMethodName(), "Fail");
            ExtentLogger.fail(result.getThrowable().toString());
            ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * <p>Marks the test as pass and logs it in the report
	 * send the script status to ELK live dashBoard</p><br>
	 * @see org.demo.reports.ExtentLogger
	 * @see org.demo.utils.ELKRealTimeDashBoardUtils
	 */
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped !", true);
            ELKRealTimeDashBoardUtils.sendStatisticsELK(result.getMethod().getMethodName(), "Skip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    /**
     * For now, we're not using this
     */
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
	/**
     * For now, we're not using this
     */
    }


}
