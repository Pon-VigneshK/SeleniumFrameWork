package org.demo.listeners;

import org.demo.constants.FrameWorkConstants;
import org.demo.utils.JsonUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements {@link org.testng.IMethodInterceptor} to leverage the abstract methods
 * Mostly used to read the data from json and decides on which tests needs to run.
 * 
 * <pre>Please make sure to add the listener details in the testng.xml file</pre>
 * 
 * @author Pon Vignesh
 * Nov , 2022
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.utils.JsonUtils
 */
public class MethodInterceptor implements IMethodInterceptor {
	
	/**
	 * Intercepts the existing test methods and changes the annotation value at the run time
	 * Values are fetched from the json.
	 * User has to choose yes/No in the database runner list table.
	 * Suppose if there are 3 tests named a,b,c needs to be run, it reads the runner list json and understand user wants to 
	 * run only a and c, then it will return the same after performing the comparisons.
	 */
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methodInterceptorlist, ITestContext iTestContext) {
        List<IMethodInstance> results = new ArrayList<>();
        // generate runner list json
        JsonUtils.generateRunnerListJsonData();
        List<Map<String, Object>> testcaselistfromjsonfile = JsonUtils.getTestDetails(FrameWorkConstants.getRunmanager(), FrameWorkConstants.getTestcaselist());
        for (int i = 0; i < methodInterceptorlist.size(); i++) {
            for (int j = 0; j < testcaselistfromjsonfile.size(); j++) {
                if (methodInterceptorlist.get(i).getMethod().getMethodName().equalsIgnoreCase(String.valueOf(testcaselistfromjsonfile.get(j).get("testcasename"))) &&
                        String.valueOf(testcaselistfromjsonfile.get(j).get("execute")).equalsIgnoreCase("yes")) {
                    methodInterceptorlist.get(i).getMethod().setDescription(String.valueOf(testcaselistfromjsonfile.get(j).get("testdescription")));
                    methodInterceptorlist.get(i).getMethod().setPriority(Integer.parseInt(String.valueOf(testcaselistfromjsonfile.get(j).get("priority"))));
                    results.add(methodInterceptorlist.get(i));
                }
            }
        }


        return results;
    }

}
