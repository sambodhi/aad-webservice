package com.aad.ws.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.aad.ws.dto.AppDetails;
import com.aad.ws.service.AppService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

 
@Component  
//@Scope("request") 
@Path("/application")
public class AppResource {
	
	@Autowired
	private AppService appService;
	
	private static final Logger logger = Logger.getLogger(AppResource.class);
	
	@GET
	@Path("details/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppDetails getAppDetails(final @PathParam("appId") String id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= "+id);
		AppDetails appDetails = new AppDetails();
		//stub
		appDetails.setName("survey");
		appDetails.setDescription("blah blah");
		appDetails.setSize("50 MB");
		return appDetails;
	}
 
}
