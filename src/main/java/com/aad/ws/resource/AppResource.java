package com.aad.ws.resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.domain.Application;
import com.aad.ws.dto.AppDetails;
import com.aad.ws.exception.InvalidAttribute;
import com.aad.ws.service.AppService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/application")
public class AppResource {

	@Autowired
	private AppService appService;
	private static final Logger logger = Logger.getLogger(AppResource.class);
	
	@GET
	@Path("{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppDetails getAppInfo(final @PathParam("appId") long id) {
		logger.debug("Resource >> App >> getAppDetails >> param: id= " + id);
		AppDetails appDetails = appService.getAppDetails(id);
		
		return appDetails;
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateApplication(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			//@FormDataParam("name") String name,
			@FormDataParam("appIDCreate") long appId
			//@FormDataParam("description") String description,
			//@FormDataParam("type") int typeId,
			//@FormDataParam("category") int categoryId,
			//@FormDataParam("size") String size,
			//@FormDataParam("developer") String developer
			) throws InvalidAttribute {

		logger.debug("Parameters obtained from request: + " +
				//" name ="+ name +
				//", description =" + description +
				//", category =" + categoryId +
				//", type =" + typeId +
				", fileDetail =" + fileDetail +
				", fileData (is null?) =" + (uploadedInputStream==null) +
				//", size =" + size +
				//", developer =" + developer+
				", appId =" + appId);
		
		//validate request
		if(uploadedInputStream == null){
			 throw new InvalidAttribute("file", "file data missing");
		}
		if(fileDetail == null){
			 throw new InvalidAttribute("file", "fileDetail missing");
		}
		if(appId<1){
			 throw new InvalidAttribute("appId", "appId missing");
		}
//		if(StringUtils.isEmpty(description)){
//			 throw new InvalidAttribute("description", "description missing");
//		}
//		if(typeId == 0){
//			 throw new InvalidAttribute("type", "type missing");
//		}
//		if(categoryId == 0){
//			 throw new InvalidAttribute("category", "category missing");
//		}
//		if(StringUtils.isEmpty(developer)){
//			 throw new InvalidAttribute("developer", "developer missing");
//		}
		
		Application application = new Application(appId);
		logger.debug("Application params received from request: " + application);
		
		Application app = appService.storeFile(fileDetail.getFileName(), uploadedInputStream, application);
		if(app != null)
			return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(app).build();
		return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("Updated Failed").build();
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createApplication(
			@FormDataParam("name") String name,
			@FormDataParam("description") String description,
			@FormDataParam("type") int typeId,
			@FormDataParam("category") int categoryId,
			@FormDataParam("size") String size,
			@FormDataParam("developer") String developer
			) throws InvalidAttribute {

		logger.debug("Parameters obtained from request: + " +
				" name ="+ name +
				", description =" + description +
				", category =" + categoryId +
				", type =" + typeId +
				", size =" + size +
				", developer =" + developer);
		
		//validate request
		if(StringUtils.isEmpty(name)){
			 throw new InvalidAttribute("name", "name missing");
		}
		if(StringUtils.isEmpty(description)){
			 throw new InvalidAttribute("description", "description missing");
		}
		if(typeId == 0){
			 throw new InvalidAttribute("type", "type missing");
		}
		if(categoryId == 0){
			 throw new InvalidAttribute("category", "category missing");
		}
		if(StringUtils.isEmpty(developer)){
			 throw new InvalidAttribute("developer", "developer missing");
		}
		
		Application application = new Application(0, categoryId, typeId, name,  size, developer, description);
		logger.debug("Application params received from request: " + application);
		
		Application app = appService.createApplication(application);
		logger.info("Application Created With Id:"+app.getAppId());
		return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(app).build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteApplication(
			@FormDataParam("appIDDelete") long appId
			) throws InvalidAttribute {

		logger.debug("Parameters obtained from request: + " +
				", appId =" + appId);
		Application application = new Application(appId);
		logger.debug("Application params received from request: " + application);
		
		boolean deleteSuccess = appService.deleteFile(application);
		logger.info("Delete Success:" + deleteSuccess);
		if(deleteSuccess)
			return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(application).build();
		return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("Delete Failed").build();
	}
	
}
