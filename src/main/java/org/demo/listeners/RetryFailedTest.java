package org.demo.listeners;

import org.demo.enums.ConfigProperties;
import org.demo.utils.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Implements {@link IRetryAnalyzer}.<p>
 * Helps in rerunning the failed tests.<p>
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 */
public class RetryFailedTest implements IRetryAnalyzer {

    private static int count=0;
    private static int retryCount=0;

    /**
	 * Return true when needs to be retried and false otherwise.
	 * Maximum will retry for one time.
	 * Retry will happen if user desires to and set the value in the property file
	 */
    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean retryState =false;
        if (PropertyUtils.getValue(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")) {
            //compare the retries
            retryState = count < retryCount;
            count++;
            return retryState;
        }
        else
            return retryState;

    }
}