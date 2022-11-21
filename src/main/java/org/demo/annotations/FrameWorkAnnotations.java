package org.demo.annotations;

/**
 * <p>Framework Annotation is user built annotation which is annotated on top of test methods to log the author details and
 * category details to the extent report.<br>
 *
 * Runtime retention value indicate that this annotation will be available at run time for reflection operations.
 * </p>
 *
 * Nov 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.tests;
 * @see org.demo.enums.CategoryType
 */
import org.demo.enums.CategoryType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface FrameWorkAnnotations {
    /**
     * Store the authors who created the tests in String[]
     * Mandatory to enter at-least one value
     * @author Pon Vignesh
     * Nov 2022
     */
    String[] testAuthor() default "";
    /**
     * Stores the category in form of Enum Array. Include the possible values in {@link org.demo.enums.CategoryType}
     *  @author Pon Vignesh
     *  Nov 2022
     */
    CategoryType[] catagory();

}
