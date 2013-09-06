/**
 * 
 */
package com.foresters.oAuth;

/**
 * @author abhijeet.chaudhury
 *
 */
public class CustomOAuthException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String  message = null;

	/**
	 * overriding the CustomOAuthException(String message) .
	 * @param message 
	 */
	public CustomOAuthException(String message) {
		super(message);
		this.message = message;
	}
	/**
	 * overriding the CustomOAuthException(Exception e) .
	 * @param e 
	 */
	public CustomOAuthException(Exception e){
		super(e);
		
		
	}	
	/**
	 * overriding the CustomOAuthException(String exceptionMessage , Exception e) .
	 * @param exceptionMessage 
	 * @param e 
	 */
	public CustomOAuthException(String exceptionMessage, Exception e){
		super(exceptionMessage, e);
		this.message = exceptionMessage;
		e.fillInStackTrace();
		
	}
	
	public String toString()
	{
		return message;
	}

}
