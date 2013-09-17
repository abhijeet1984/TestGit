/**
 * 
 */
package com.learning.wipro;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author AB277176
 *
 */
@Path("/App")
public class RestService {
	
	@GET
	@Path("/Rest")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello(@QueryParam("name") String uName){
		return "Hello to User :"+uName ;
	}

}
