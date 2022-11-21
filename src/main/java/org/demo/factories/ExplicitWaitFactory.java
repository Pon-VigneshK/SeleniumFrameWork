package org.demo.factories;

import org.demo.constants.FrameWorkConstants;
import org.demo.enums.WaitStrategy;
import org.demo.exceptions.FrameWorkExceptions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.demo.driver.DriverManager.getThreaddriver;

/**
 * Explicit wait factory produces different waits before operating on webElement
 * 
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 * @see org.demo.pages.basepage.BasePageActions
 */
public final class ExplicitWaitFactory {
	
	/**
     * Private constructor to avoid external instantiation
     */
    private ExplicitWaitFactory(){}

	/**
	 * 
	 * @author Pon Vignesh
	 * Nov , 2022
	 * @param waitStrategy Strategy to be applied to find a webElement {@link org.demo.enums.WaitStrategy}
	 * @param by By locator of the webElement
	 * @return webElement Locates and return the webElement
	 */
    public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by){

        WebElement element;
        WebDriverWait webDriverWait = new WebDriverWait(getThreaddriver(), Duration.ofSeconds(FrameWorkConstants.getExplicitwait()));
        try {
            switch (waitStrategy){

                case CLICKABLE:
                    element = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
                    break;
                case PRESENCE:
                    element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
                    break;
                case VISIBLE:
                    element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    break;
                case NONE:
                    element = getThreaddriver().findElement(by);
                    break;
                default:
                    throw new IllegalStateException("Please enter correct wait option from the list: " + waitStrategy);
            }
        }
        catch (TimeoutException e){
            throw new FrameWorkExceptions("Wait time exceed, Unable to locate the element", e);
        }

        return element;
    }


}
