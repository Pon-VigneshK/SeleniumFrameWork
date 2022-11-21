package org.demo.exceptions;

/**
 * BaseException for the framework. Extends Runtime Exception and can be thrown anywhere to terminate a program
 * 
 * 
 * Nov, 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FrameWorkExceptions extends RuntimeException{
	/**
	 * Pass the message that needs to be appended to the stack-trace
	 * @param message Details about the exception or custom message
	 */
    public FrameWorkExceptions(String message){ super(message); }
	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause Pass the enriched stack-trace or customized stack-trace
	 */
    public FrameWorkExceptions(String message, Throwable cause){ super(message, cause); }

}
