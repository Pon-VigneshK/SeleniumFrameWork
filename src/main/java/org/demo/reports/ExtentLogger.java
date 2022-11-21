package org.demo.reports;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.demo.enums.ConfigProperties;
import org.demo.utils.PropertyUtils;


import static org.demo.utils.ScreenSHotUtils.getBase64Image;

/**
 * ExtentLogger class helps to achieve test report for the current test
 * 
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.reports.ExtentManager
 * 
 */
public final class ExtentLogger {
	/**
	 * Private constructor to avoid external instantiation
	 */
    private ExtentLogger(){}

    /**
     * Pass method is used to print the pass message details in the extent report
     * @param message is the custom text/string passed from user defined method
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void pass(String message){ExtentManager.getExtentTest().pass(message);}
    /**
     * Fail method is used to print the fail message details in the extent report
     * @param message is the custom text/string passed from user defined method
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void fail(String message){
        ExtentManager.getExtentTest().fail(message);
    }
    /**
     * Skip method is used to print the skip message details in the extent report
     * @param message is the custom text/string passed from user defined method
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void skip(String message){
        ExtentManager.getExtentTest().skip(message);
    }
    /**
     * Info method is used to print the info message details in the extent report
     * @param message is the custom text/string passed from user defined method
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void info(String message){
        ExtentManager.getExtentTest().info(message);
    }
    /**
     * Pass with screenshot method is used to print the pass message details with current web page screenshot in the extent report
     * @param message is the custom text/string passed from user defined method
     * @param isScreenshotNeeded if screenshot needed this parameter should be set to true 
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void pass(String message, boolean isScreenshotNeeded) {
        if(PropertyUtils.getValue(ConfigProperties.PASSEDSTEPSCREENSHOTS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ExtentManager.getExtentTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }
        else
            pass(message);

    }
    /**
     * Fail with screenshot method is used to print the fail message details with current web page screenshot in the extent report
     * @param message is the custom text/string passed from user defined method
     * @param isScreenshotNeeded if screenshot needed this parameter should be set to true 
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void fail(String message, boolean isScreenshotNeeded) {
        if(PropertyUtils.getValue(ConfigProperties.FAILEDSTEPSCREENSHOT).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }else
            fail(message);

    }
    /**
     * Skip with screenshot method is used to print the skip message details with current web page screenshot in the extent report
     * @param message is the custom text/string passed from user defined method
     * @param isScreenshotNeeded if screenshot needed this parameter should be set to true 
     * Nov , 2022
     * @author Pon Vignesh
     */
    public static void skip(String message, boolean isScreenshotNeeded) {
        if(PropertyUtils.getValue(ConfigProperties.SKIPPEDSTEPSCREENSHOT).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }
        else
            skip(message);
    }


}
