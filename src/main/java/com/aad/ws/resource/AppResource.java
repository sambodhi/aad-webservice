package com.aad.ws.resource;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dto.AppDetails;
import com.aad.ws.service.AppService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
	
	/*@GET
	@Path("details/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AppDetails> storeApp(final @PathParam("appId") String id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= " + id);

		listApps(APPSDIR);
	
		return appDetails;
	}*/
	
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("name") String name,
			@FormDataParam("description") String description,
			@FormDataParam("type") String type,
			@FormDataParam("category") String category,
			@FormDataParam("size") String size
			) {

		logger.debug("Parameters obtained from request: + " +
				"name ="+ name +
				"description =" + description +
				"category =" + category +
				"type =" + type +
				"size =" +size);
		//TODO: validate request
		
		//save to file system
		String outputLoc = appService.uploadFile(fileDetail.getFileName(), uploadedInputStream);

		//TODO: save to database
		
		return Response.status(200).entity(outputLoc).build();

	}
	
}
