package org.demo.exceptions;

/**
 * Runtime Exception occurs when the path given for json path is incorrect.
 * Nov, 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 * @see org.demo.constants.FrameWorkConstants
 */
@SuppressWarnings("serial")
public class JsonExceptions extends RuntimeException {

	/**
	 * Pass the message that needs to be appended to the stack-trace
	 * @param message Details about the exception or custom message
	 */
    public JsonExceptions(String message) {
        super(message);
    }
    /**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause Pass the enriched stack-trace or customized stack-trace
	 */
    public JsonExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
