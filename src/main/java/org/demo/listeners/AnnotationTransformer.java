package org.demo.listeners;

import org.demo.utils.DataProviderUtils;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Implements {@link org.testng.IAnnotationTransformer} to leverage certain functionality like updating the annotations of test
 * methods at runtime.
 * 
 * @author Pon Vignesh
 * Nov , 2022
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.utils.DataProviderUtils
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	/**
	 * Helps in setting the dataProvider, dataProvider class and retry analyzer annotation to all the test methods
	 * at run time. 
	 */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        //
        annotation.setDataProvider("getJsonTestData");
        annotation.setDataProviderClass(DataProviderUtils.class);
        annotation.setRetryAnalyzer(RetryFailedTest.class);


    }
}
