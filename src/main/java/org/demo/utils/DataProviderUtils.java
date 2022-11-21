package org.demo.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.*;

import static org.demo.utils.JsonUtils.generateTestDataJson;
import static org.demo.utils.JsonUtils.getTestDataDetails;

/**
 * Holds the data provider for all the test cases in the framework.
 *
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 */
public final class DataProviderUtils {
	/**
	 * Private constructor to avoid external instantiation
	 */
    private DataProviderUtils(){}

    private static List<Map<String, Object>> testDataFromJson = new ArrayList();

    /**
	 * Acts as a data provider for all the test cases.
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @param m {@link Method} holds the information about the testcases at runtime
	 * @return Object[] containing the List. Each index of the list contains HashMap and each of them
	 * contains the test data needed to run the iterations.
	 * @version 1.0
	 * @since 1.0<br>
	 * @see org.demo.tests;
	 * @see org.demo.listeners.AnnotationTransformer
	 */
    //@DataProvider(name = "getJsonTestData")
    public static Object[][] getJsonData(Method method) {
        String testName = method.getName();
        List<Map<String, Object>> iterationList = new ArrayList();
        if (testDataFromJson.isEmpty()) {
            //get the data from database
            generateTestDataJson();
            //Store the test details in list
            testDataFromJson = getTestDataDetails();
        }


        for (int i = 0; i < testDataFromJson.size(); i++) {
            if (String.valueOf(testDataFromJson.get(i).get("testcasename")).equalsIgnoreCase(testName) &&
                    String.valueOf(testDataFromJson.get(i).get("execute")).equalsIgnoreCase("yes")) {
                iterationList.add(testDataFromJson.get(i));
            }

        }
        // remove duplicates from the previous list to avoid unwanted run from data provider
        Set<Map<String, Object>> set = new HashSet(iterationList);
        iterationList.clear();
        iterationList.addAll(set);
        return (Object[][]) iterationList.toArray();
    }

}
