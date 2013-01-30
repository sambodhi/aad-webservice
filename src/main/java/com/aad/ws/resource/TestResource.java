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

import com.aad.ws.dto.UserTest;
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
	public UserTestCollection getTestDetails(final @PathParam("testID") int id){
		//TODO: uncomment when database done
		UserTestCollection tests = testsService.getTestDetails(id); 
		
		//remove it!
//		UserTestCollection tests = new UserTestCollection();
//		List<UserTest> testList = new ArrayList<UserTest>();
//		
//		
//		UserTest test1 = new UserTest();
//		test1.setScore(64);
//		test1.setTestId(12);
//		test1.setTime(100);
//		test1.setQuesAttented(2);
//		
//		UserTest test2 = new UserTest();
//		test2.setScore(59.9);
//		test2.setTestId(13);
//		test2.setTime(101);
//		test2.setQuesAttented(5);
//		
//		testList.add(test1);
//		testList.add(test2);
//		
//		tests.setUserTests(testList);
		
		
		return tests;
	}
}