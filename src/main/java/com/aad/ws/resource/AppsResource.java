package com.aad.ws.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dto.AppCollection;
import com.aad.ws.service.AppService;

@Component
@Path("/applications")
public class AppsResource {

	private static final Logger logger = Logger.getLogger(AppsResource.class);
	
	@Autowired
	private AppService appService;
	
	@GET
	@Path("{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppCollection getAppsForCategory(final @PathParam("categoryId") long id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= " + id);
		//TODO:uncomment when database is accessible
		AppCollection appsCol = appService.getAppsForCategory(id);
		
		return appsCol;
	}
}
