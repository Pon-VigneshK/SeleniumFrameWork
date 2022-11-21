package com.gcit.utils;

/**
 * Constructs a dynamic xpath at run time.
 *
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 */
public final class DynamicXpathUtils {
	/**
	 * Private constructor to avoid external instantiation
	 */
    private DynamicXpathUtils(){}

    /**
	 * Receives a matching string, replace the matching with the value and return to the caller
     * Nov , 2022
     * @author Pon Vignesh
	 * @param xpath Xpath with matching string
	 * @param value value to be replaced in place of matching
	 * @return dynamic xpath string
	 * 
	 */
    public static String getDynamicXpath(String xpath, String value){
        return String.format(xpath, value);
    }
    public static String getDynamicXpath(String xpath, String stringValueOne, String stringValueTwo){
        return String.format(xpath, stringValueOne, stringValueTwo);
    }
    public static String getDynamicXpath(String xpath, String stringValueOne, String stringValueTwo, String stringValueThree){
        return String.format(xpath, stringValueOne, stringValueTwo,stringValueThree);
    }


}
