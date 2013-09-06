/**
 * 
 */
package com.foresters.oAuth;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author abhijeet.chaudhury
 *
 */
public class APILogger {
	
	private static Logger logger = null ;
	
	private static boolean isInitialize = false ;
	
	private APILogger(){
		
	}
	
	private static void getLoggerInitialize(){
		if(logger==null){
			logger = getAPILogger();
			isInitialize = true;
		}
	}
	 
	
	private static Logger getAPILogger() {
		
		if(logger == null){
		try{
			
		
			logger = Logger.getLogger(APILogger.class.getName());
			System.out.println("getting the logger "+logger.getName());
			
		}
		catch (Exception e) {
			System.out.println("exception:"+e.getMessage());
			
		}
		}
		 return logger;
	}
	
	
	
	
	
	
	private static void log(Object arg_ClassName,String arg_DebugMessage,Throwable arg_throwable,String type){
		if(!isInitialize)getLoggerInitialize();

		String className = "";
		int nIndex =0;
		String methodName ="";
		if( arg_ClassName instanceof String )
		{
			 className = (String)arg_ClassName;			
			 nIndex = className.lastIndexOf('.');
			 methodName = className.substring( nIndex + 1 );			
			 className = className.substring(0,nIndex);
			
		}
		else
		{
			className = arg_ClassName.getClass().getName();
			nIndex = className.lastIndexOf('.');
			className = className.substring( nIndex + 1 );
		}
		

		
		StringBuffer loggedMessage = new StringBuffer().append( className ).append(" -- ").append( methodName ).append(" -- ").append( arg_DebugMessage );
		if(type.equalsIgnoreCase("ERROR")){
			if(arg_throwable==null){
				logger.error(loggedMessage.toString());
			}
			else{
				System.out.println("Got an Error "+loggedMessage.toString() );
				logger.error(loggedMessage.toString(),arg_throwable);
			}
		}
		if(type.equalsIgnoreCase("DEBUG")){
			logger.debug(loggedMessage.toString());
		}
		
		if(type.equalsIgnoreCase("INFO")){
			logger.info(loggedMessage.toString());
		}
		if(type.equalsIgnoreCase("FATAL")){
			if(arg_throwable==null){
				logger.fatal(loggedMessage.toString());
			}
			else{
				System.out.println("Got an Error "+loggedMessage.toString() );
				logger.fatal(loggedMessage.toString(),arg_throwable);
			}
		}
	}
	public static void logDebug(Object arg_ClassName,String arg_DebugMessage){
		log(arg_ClassName,arg_DebugMessage,null,"DEBUG");
		
	}
	public static void logInfo(Object arg_ClassName,String arg_DebugMessage){
		log(arg_ClassName,arg_DebugMessage,null,"INFO");
		
	}
	public static void logError(Object arg_ClassName,String arg_DebugMessage,Throwable arg_throwable){
		log(arg_ClassName,arg_DebugMessage,arg_throwable,"ERROR");
		
	}
	
	public static void logFatal(Object arg_ClassName,String arg_DebugMessage,Throwable arg_throwable){
		log(arg_ClassName,arg_DebugMessage,arg_throwable,"FATAL");
		
	}

}
