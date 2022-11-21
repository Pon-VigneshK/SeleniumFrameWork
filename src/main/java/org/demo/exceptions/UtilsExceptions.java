package org.demo.exceptions;

/**
 * Runtime Exception occurs when the usage of utils files
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.utils.DataBaseConnection;
 * @see org.demo.utils.DataProviderUtils;
 * @see org.demo.utils.DecodeUtils;
 * @see org.demo.utils.DynamicXpathUtils;
 * @see org.demo.utils.ELKRealTimeDashBoardUtils;
 * @see org.demo.utils.JsonUtils;
 * @see org.demo.utils.PropertyUtils;
 * @see org.demo.utils.ScreenSHotUtils;
 */

@SuppressWarnings("serial")
public class UtilsExceptions extends RuntimeException{

	/**
	 * Pass the message that needs to be appended to the stack-trace
	 * @param message Details about the exception or custom message
	 */
    public UtilsExceptions(String message){
        super(message);
    }
    /**
   	 * 
   	 * @param message Details about the exception or custom message
   	 * @param cause Pass the enriched stack-trace or customized stack-trace
   	 */
    public UtilsExceptions(String message, Throwable cause){
        super(message, cause);
    }

}
