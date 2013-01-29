package com.aad.ws.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dto.AppCollection;
import com.aad.ws.dto.AppDetails;
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
	public AppCollection getAppsForCategory(final @PathParam("categoryId") int id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= " + id);
		//TODO:uncomment when database is accessible
		AppCollection appsCol = appService.getAppsForCategory(id);
		
		
		//remove it!
//		AppDetails appDetails = new AppDetails();
//		appDetails.setCategoryName("Maths");
//		appDetails.setDescription("maths app");
//		appDetails.setName("app1");
//		appDetails.setUrl("http://schoolware.cs.ucl.ac.uk/web/Apps/jshd-dj.jar");
//		
//		AppDetails appDetails1 = new AppDetails();
//		appDetails1.setCategoryName("Maths");
//		appDetails1.setDescription("maths app");
//		appDetails1.setName("app1");
//		appDetails1.setUrl("http://schoolware.cs.ucl.ac.uk/web/Apps/jshd-dj.jar");
//		
//		List<AppDetails> apps = new ArrayList<AppDetails>();
//		apps.add(appDetails);
//		apps.add(appDetails1);
//		AppCollection appCol = new AppCollection();
//		appCol.setApps(apps);
		
		return appsCol;
	}
}
