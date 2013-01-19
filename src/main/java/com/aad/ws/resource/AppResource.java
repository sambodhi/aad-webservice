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
	private static ArrayList<String> files = new ArrayList<String>();
	private static final Logger logger = Logger.getLogger(AppResource.class);
	private static final String APPSDIR = "C:\\Users\\MConstantinides\\git\\aad-webservice\\Apps";
	
	@GET
	@Path("details/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AppDetails> getAppDetails(final @PathParam("appId") String id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= " + id);
		ArrayList<AppDetails> appDetails = new ArrayList<AppDetails>();

		printFnames(APPSDIR);
		String desc = "";

		for (int i = 0; i < files.size(); i++) {

			AppDetails ap = new AppDetails();
			ap.setName(files.get(i));
			ap.setDescription("test");
			ap.setSize("test1");
			appDetails.add(ap);
		
		}

		// stub
		//appDetails.setName(id);
		//appDetails.setDescription(desc);
		//appDetails.setSize("50 MB");
		return appDetails;
	}

	public static void printFnames(String sDir) {
		File[] faFiles = new File(sDir).listFiles();

		for (File file : faFiles) {
			if (file.getName().matches("^(.*?)")) {
				files.add(file.getAbsolutePath());
				// System.out.println(file.getAbsolutePath());
			}
			if (file.isDirectory()) {
				printFnames(file.getAbsolutePath());
			}
		}
	}
}
