package com.aad.ws.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dto.UserTestCollection;
import com.aad.ws.service.TestsService;


@Component
@Path("/test")
public class TestResource {

	@Autowired
	private TestsService testsService;
	private static final Logger logger = Logger.getLogger(TestResource.class);
	
	@GET
	@Path("{testID}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserTestCollection getTestDetails(final @PathParam("testID") long id){
		UserTestCollection tests = testsService.getTestDetails(id); 
		
		return tests;
	}
}