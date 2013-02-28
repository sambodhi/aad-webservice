package com.aad.ws.resource;

import javax.ws.rs.Path;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.aad.ws.dto.UserUQuestionsCollection;
import com.aad.ws.service.UQuestionsService;

@Component
@Path("/question")
public class UserUQuestionsResource {

	@Autowired
	private UQuestionsService questionsService;
	private static final Logger logger = Logger.getLogger(UserUQuestionsResource.class);
	
	@GET
	@Path("{questionID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserUQuestions(final @PathParam("questionID") long id){
		UserUQuestionsCollection questions = questionsService.getUserUQuestions(id); 
		
		//return questions;
		return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(questions).build();
	}
}
