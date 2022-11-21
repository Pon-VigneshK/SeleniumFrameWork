package org.demo.Base;

import org.demo.constants.FrameWorkConstants;
import org.demo.driver.Driver;
import org.testng.annotations.*;
import java.io.IOException;
/**
 * Acts as a parent class for all the test classes in this framework.
 * All the test classes needs to extend this class. This class is responsible for invoking and terminating
 * browser under test.
 * 
 * @author Pon Vignesh
 * Nov, 2022
 * @version 1.0
 * @since 1.0<br>
 */

public class BaseSetup {
    
	/**
	 * Get the current environment name
	 *
	 * @author Pon Vignesh
	 * Nov, 2022
	 * @param environment get the current environment name from the testNG suite file
	 */
    @Parameters({"environment"})
    @BeforeSuite(alwaysRun = true)
    protected void setUpEnvironment(@Optional("QA") String environment){
        FrameWorkConstants.setEnvironment(environment.toUpperCase());
    }


    /**
	 * Invokes a new browser instance
	 *
	 * @author Pon Vignesh
	 * Nov, 2022
	 * @param browser used to assign the respective browser for the current test class
	 */
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    protected void driverSetup( String browser){
        Driver.initDriver(browser);
    }

    /**
	 * Terminates the browser instance
	 * @author Pon Vignesh
	 * Nov, 2022
	 */
    @AfterClass(alwaysRun = true)
    protected void quitBrowser() {
		Driver.closeDriver();
//		Driver.quitDriver();
    }


}
