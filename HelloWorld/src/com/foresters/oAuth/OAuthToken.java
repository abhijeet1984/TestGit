/**
 * 
 */
package com.foresters.oAuth;

import java.io.Serializable;

/**
 * @author abhijeet.chaudhury
 *
 */
public class OAuthToken implements Serializable{
	
	private String oauthToken ;
	
	private String oauthTokenSecret ;
	
	private String oauthTimestamp ;
	
	private String oauthNonce ;
	
	private String producerId ;
	
	public OAuthToken(){
		
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}

	public void setOauthTokenSecret(String oauthTokenSecret) {
		this.oauthTokenSecret = oauthTokenSecret;
	}

	public String getOauthTimestamp() {
		return oauthTimestamp;
	}

	public void setOauthTimestamp(String oauthTimestamp) {
		this.oauthTimestamp = oauthTimestamp;
	}

	public String getOauthNonce() {
		return oauthNonce;
	}

	public void setOauthNonce(String oauthNonce) {
		this.oauthNonce = oauthNonce;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	

	
	
	

}
