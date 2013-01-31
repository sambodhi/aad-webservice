package com.aad.ws.resource;

import javax.ws.rs.Path;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.aad.ws.dto.UQuestionsCollection;
import com.aad.ws.service.UQuestionsService;

@Component
@Path("/questions")
public class UQuestionsResource {

	@Autowired
	private UQuestionsService questionsService;
	private static final Logger logger = Logger.getLogger(UQuestionsResource.class);
	
	@GET
	@Path("{testID}")
	@Produces(MediaType.APPLICATION_JSON)
	public UQuestionsCollection getUQuestions(final @PathParam("testID") long id){
		//TODO: uncomment when database done
		UQuestionsCollection questions = questionsService.getQuestions(id); 
		
	
		return questions;
	}
}
