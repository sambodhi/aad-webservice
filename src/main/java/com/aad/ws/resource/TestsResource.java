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

import com.aad.ws.dto.Test;
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
	public TestsCollection getTests(final @PathParam("testID") int id){
		//TODO: uncomment when database done
		TestsCollection tests = testsService.getTests(id); 
		
		//remove it!
//		TestsCollection tests = new TestsCollection();
//		
//		List<Test> testList = new ArrayList<Test>();
//		
//		Test test1 = new Test();
//		test1.setTestId(15);
//		test1.setTestName("MathTest");
//		
//		Test test2 = new Test();
//		test2.setTestId(16);
//		test2.setTestName("BiologyTest");
//		
//		testList.add(test1);
//		testList.add(test2);
//		
//		tests.setTest(testList);
//		
		return tests;
	}
}
