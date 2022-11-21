package org.demo.reports;

import com.aventstack.extentreports.ExtentTest;
/**
 * ExtentManager class helps to achieve thread safety for the {@link com.aventstack.extentreports.ExtentTest} instance.
 *
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.driver.Driver
 */

public final class ExtentManager extends ThreadLocal<ExtentTest> {
	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExtentManager(){}

    private static ThreadLocal<ExtentTest> extenttest= new ThreadLocal<>();

    /**
	 * Returns the thread safe {@link com.aventstack.extentreports.ExtentTest} instance fetched from ThreadLocal variable.
	 * Nov , 2022
	 * @author Pon Vignesh
	 * @return Thread safe {@link com.aventstack.extentreports.ExtentTest} instance.
	 */
    public static ExtentTest getExtentTest() {
        return extenttest.get();
    }

	/**
	 * Set the {@link com.aventstack.extentreports.ExtentTest} instance to thread local variable
	 *
	 * Nov , 2022
	 * @author Pon Vignesh
	 * @param test {@link com.aventstack.extentreports.ExtentTest} instance that needs to saved from Thread safety issues.<p>
	 */
    public static void setExtentTest(ExtentTest test) {
        extenttest.set(test);
    }
    /**
	 * Calling remove method on ThreadLocal variable ensures to set the default value to ThreadLocal variable.
	 * It is much safer than assigning null value to ThreadLocal variable.
	 * Nov , 2022
	 * @author Pon Vignesh
	 */
    public static void unExtentTest(){
        extenttest.remove();
    }

}
