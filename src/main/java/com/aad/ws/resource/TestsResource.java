package com.aad.ws.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dto.TestsCollection;
import com.aad.ws.service.TestsService;


@Component
@Path("/tests")
public class TestsResource {

	@Autowired
	private TestsService testsService;
	private static final Logger logger = Logger.getLogger(TestsResource.class);
	
	@GET
	@Path("{testID}")
	@Produces(MediaType.APPLICATION_JSON)
	public TestsCollection getTests(final @PathParam("testID") long id){
		TestsCollection tests = testsService.getTests(id); 
		
		return tests;
	}
}
