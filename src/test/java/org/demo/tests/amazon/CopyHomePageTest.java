package org.demo.tests.amazon;

import com.google.common.util.concurrent.Uninterruptibles;
import org.assertj.core.api.Assertions;
import org.demo.Base.BaseSetup;
import org.demo.annotations.FrameWorkAnnotations;
import org.demo.enums.CategoryType;
import org.demo.enums.ConfigProperties;
import org.demo.pages.amazon.AmazonHomePage;
import org.demo.utils.PropertyUtils;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

import static org.demo.driver.DriverManager.getThreaddriver;

/**
 * Contains the test cases of Amazon website
 * 
 * @author Pon Vignesh
 * Nov , 2022
 * @version 1.0
 * @since 1.0<br>
 * @see BaseSetup
 */
public final class CopyHomePageTest extends BaseSetup {
    
	/**
	 * Private constructor to avoid external instantiation
	 */
	private CopyHomePageTest(){}
    
    /**
	 * Test Name mentioned here should match the column name "TestCaseName" in database.This is mandatory to run this
	 * test. Otherwise, it will be ignored. <p>
	 * The match has to be there in both of the RunnerList Json and TestCaseName
	 * Set the authors who have the created the test which will be logged to the reports.
	 * Set the category which this particular test case belongs to
	 * @author Pon Vignesh
	 * Nov , 2022
	 * @param map HashMap containing all the values of test data needed to run the tests
	 */
    @FrameWorkAnnotations(testAuthor = {"Pon Vignesh"}, catagory = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Test
    public void TC002_amazonHomePageTest(HashMap<String,String> map){
        getThreaddriver().get(PropertyUtils.getValue(ConfigProperties.AMAZONURL));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        String title = new AmazonHomePage().clickHamburger().clickOnFirstSubMenu(map.get("searchItem"), map.get("searchItem")).clickOnSubMenu(map.get("subSearchItem"), map.get("subSearchItem")).getCurrentTitle();
        Assertions.assertThat(title).isNotNull().isNotBlank();
    }
}
