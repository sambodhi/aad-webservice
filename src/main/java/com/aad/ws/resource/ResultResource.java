package com.aad.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dao.ResultDAO;
import com.aad.ws.domain.Results;
import com.aad.ws.service.AppService;
import com.aad.ws.service.submitResultService;



@Component
@Path("/result")
public class ResultResource {
	
	@Autowired
	private submitResultService resultService;
	private static final Logger logger = Logger.getLogger(ResultResource.class);
	
	@POST
	@Path("submit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response StoreInDB(Results results) {
		String rere = "Results saved : " + results;
		logger.debug("*******************"+ results.gettotalquestions());
		
		results=resultService.subResults(results);
		
		return Response.status(201).entity(results).build();
	}
}
