package org.demo.utils;

import org.demo.enums.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.time.LocalTime;
import java.util.HashMap;
import static  io.restassured.RestAssured.given;
/**
 * ELKRealTimeDashBoardUtils class used to push the current test status to the elastic search dashboard
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 * @see org.demo.listeners.ListenerClass
 */
public final class ELKRealTimeDashBoardUtils {
	/**
	 * Private constructor to avoid external instantiation
	 */
    private ELKRealTimeDashBoardUtils(){}
    
    /**
	 * Constructs the document that needs to be injected to ElasticSearch using Map interface.
	 * Can also use a POJO to construct the json
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @param testCaseName
	 * @param status
	 */
    public static void sendStatisticsELK(String testCaseName, String status) {
        if (PropertyUtils.getValue(ConfigProperties.SENDRESULTKIBANA).equalsIgnoreCase("yes") && PropertyUtils.getValue(ConfigProperties.RUNMODE).equalsIgnoreCase("remote")) {
            HashMap<String, String> eLKMap = new HashMap();
            eLKMap.put("testcasename", testCaseName);
            eLKMap.put("status", status);
            eLKMap.put("executionTime", LocalTime.now().toString());

            Response response = given().header("Content-Type", "application/json").
                    log().
                    all().
                    body(eLKMap).
                    post(PropertyUtils.getValue(ConfigProperties.ELASTICSEARCHURL));
            Assert.assertEquals(response.statusCode(), 201);
            response.prettyPrint();
        }
    }
}
