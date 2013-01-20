package com.aad.ws.resource;

import java.io.File;
import java.util.ArrayList;

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
// @Scope("request")
@Path("/application")
public class AppResource {

	@Autowired
	private AppService appService;
	private static final Logger logger = Logger.getLogger(AppResource.class);
	private static final String APPSDIR = "C:\\Users\\MConstantinides\\git\\aad-webservice\\Apps";
	private ArrayList<AppDetails> appDetails = new ArrayList<AppDetails>();
	
	@GET
	@Path("details/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AppDetails> getAppDetails(final @PathParam("appId") String id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= " + id);

		listApps(APPSDIR);
	
		return appDetails;
	}

	
	public void listApps(String appsDir) {
		
		File [] files = new File(appsDir).listFiles();

		for (File file : files) {
			
			AppDetails ap = new AppDetails();
	
			if (!file.isDirectory()) {
				ap.setName(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\") + 1));
				ap.setUrl(file.getAbsolutePath());
				ap.setCategoryName(file.getParentFile().getAbsolutePath().substring(file.getParentFile().getAbsolutePath().lastIndexOf("\\") + 1));
				ap.setDescription("test");
				ap.setSize("test1");
				appDetails.add(ap);			
			}
			else{				
				listApps(file.getAbsolutePath());
			}
		}
	}
}
