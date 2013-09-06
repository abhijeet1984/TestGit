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
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.server.OAuthServlet;

/**
 * @author abhijeet.chaudhury
 * 
 */
public class ValidateKey extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// nothing at this point
		try {
			SampleOAuthProvider.loadConsumers(config);
		} catch (IOException e) {
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

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws CustomOAuthException, IOException {
		OutputStream out = response.getOutputStream();
		try {
			
			APILogger.logInfo("ValidateKey.processRequest", "Entering processRequest");
			OAuthMessage requestMessage = OAuthServlet
					.getMessage(request, null);
			APILogger.logInfo("ValidateKey.processRequest", "Request message:"+requestMessage);

			OAuthConsumer consumer = SampleOAuthProvider
					.getConsumer(requestMessage);
			
			APILogger.logInfo("ValidateKey.processRequest", "consumer:"+consumer.consumerKey);
			response.setContentType("text/plain");
			//OutputStream out = response.getOutputStream();
			//OAuth.formEncode(consumer.consumerKey, "authrorized :"+true);
			String s = "authorized&true";
			out.write(s.getBytes());
			out.close();

		} catch (CustomOAuthException e) {
			String s = "authorized&false"+" "+e.getMessage();
			out.write(s.getBytes());
			out.close();

			// SampleOAuthProvider.handleException(e, request, response, true);
			throw e;
		}

	}

	private static final long serialVersionUID = 1L;

}
