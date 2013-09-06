/**
 * 
 */
package com.foresters.oAuth;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;

import net.oauth.server.OAuthServlet;

/**
 * @author abhijeet.chaudhury
 *
 */
public class GenerateAccessToken extends HttpServlet{

	 @Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        // nothing at this point
	        try{
	            SampleOAuthProvider.loadConsumers(config);
	        }catch(IOException e){
	            throw new ServletException(e.getMessage());
	        }
	    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void processRequest(HttpServletRequest request, HttpServletResponse response)
     throws Exception {
		 
	APILogger.logInfo("GenerateAccessToken.processRequest", "Entering into processRequest");
	 try {
 	
     OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
     APILogger.logDebug("GenerateAccessToken.processRequest", "Request message :"+requestMessage); 
   
     OAuthConsumer consumer = SampleOAuthProvider.getConsumer(requestMessage);   
     String producerId = requestMessage.getParameter("producerId");
     
     APILogger.logDebug("GenerateAccessToken.processRequest", "Producer ID:"+producerId);
     APILogger.logDebug("GenerateAccessToken.processRequest", "Consumer :"+consumer.consumerKey);
  
     OAuthAccessor accessor = new OAuthAccessor(consumer);
    
     requestMessage.addRequiredParameters(accessor);
     APILogger.logDebug("GenerateAccessToken.processRequest", "Request message :"+requestMessage);
     
      // generate request_token and secret
     SampleOAuthProvider.generateRequestToken(accessor);

     
     response.setContentType("text/plain");
     OutputStream out = response.getOutputStream();
     String timestamp =requestMessage.getParameter("oauth_timestamp");
     APILogger.logDebug("GenerateAccessToken.processRequest", "TimeStamp :"+timestamp);
     
     OAuth.formEncode(OAuth.newList("oauth_token", accessor.requestToken,"oauth_token_secret", accessor.tokenSecret,"oauth_timestamp",requestMessage.getParameter("oauth_timestamp"),"oauth_nonce",requestMessage.getParameter("oauth_nonce")),out);
     SampleOAuthProvider.addSecurityToken(producerId,accessor.requestToken, accessor.tokenSecret, requestMessage.getParameter("oauth_timestamp"), requestMessage.getParameter("oauth_nonce"));
     
     APILogger.logInfo("GenerateAccessToken.processRequest", "Exiting into processRequest");
     out.close();
     
 } catch (Exception e){
   	throw e ;
 }
 
}

private static final long serialVersionUID = 1L;
}
