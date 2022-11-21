package org.demo.driver;

import org.demo.exceptions.FrameWorkExceptions;
import org.demo.factories.DriverFactory;

import java.net.MalformedURLException;
import java.util.Objects;

/**
 *  Driver class is responsible for invoking and closing the browsers.
 *  <p>
 *  It's also responsible for setting the driver variable to driver manager
 *  which handles the thread safety for the webdriver instance.
 *  </p>
 *  @author Pon Vignesh
 *  Nov , 2022
 *  @version 1.0
 *  @since 1.0 <br>
 *  @see org.demo.driver.DriverManager
 */


public final class Driver {
    /**
     * Private constructor to avoid external instantiation
     */
    private Driver() {
    }

    /**
     * @author Pon Vignesh
     * @param browserName passed from the {@link org.demo.Base.BaseSetup} test class. Value will be chrome and edge
     * @return It returns current webdriver instance based on the browsername
     */
    public static void initDriver(String browserName) {
        try {
            DriverManager.setThreaddriver(DriverFactory.getDriver(browserName));
        } catch (MalformedURLException e) {
            throw new FrameWorkExceptions("Browser invocation failed, please check capabilities");
        }
    }
    /**
	 * Terminates the browser instance.
	 * Sets the thread-local to default value, i.e null.
	 * @author Pon Vignesh
	 * Nov , 2022
	 */
    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getThreaddriver())) {
            DriverManager.getThreaddriver().quit();
            DriverManager.unloadDriver();
        }

    }

    /**
     * Close the browser instance.
     * @author Pon Vignesh
     * Nov, 2022
     */
    public static void closeDriver() {
        if (Objects.nonNull(DriverManager.getThreaddriver())) {
            DriverManager.getThreaddriver().close();
        }

    }
}
