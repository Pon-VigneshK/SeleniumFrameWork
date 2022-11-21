package org.demo.driver;

import org.demo.driver.Driver;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

/**
 * DriverManager class helps to achieve thread safety for the {@link org.openqa.selenium.WebDriver} instance.
 * 
 * 
 * Nov, 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see Driver
 */
public final class DriverManager extends ThreadLocal<WebDriver> {
    // to set a thread value as not null we can use this method
    private static ThreadLocal<WebDriver> threaddriver = new ThreadLocal<>();

	/**
	 * Private constructor to avoid external instantiation
	 */
    private DriverManager() {

    }
	/**
	 * Returns the thread safe {@link org.openqa.selenium.WebDriver} instance fetched from ThreadLocal variable.
	 * 
	 * @author Pon Vignesh
	 * Nov, 2022
	 * @return {@link org.openqa.selenium.WebDriver} instance.
	 */
    public static WebDriver getThreaddriver() {
        return threaddriver.get();
    }

	/**
	 * Set the WebDriver instance to thread local variable
	 * 
	 * @author Pon vignesh
	 * Nov, 2022
	 * @param dr {@link org.openqa.selenium.WebDriver} instance that needs to saved from Thread safety issues.<p>
	 */
    static void setThreaddriver(WebDriver dr) {
        if (Objects.nonNull(dr))
            threaddriver.set(dr);
    }
    /**
	 * Calling remove method on Thread-local variable ensures to set the default value to Thread-local variable.
	 * It is much safer than assigning null value to Thread-local variable.
	 * @author Pon Vignesh
	 * Nov, 2022
	 */
    static void unloadDriver() {
        threaddriver.remove();
    }
}
