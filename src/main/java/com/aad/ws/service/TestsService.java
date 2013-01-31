package com.aad.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.TestsDAO;
import com.aad.ws.domain.Test;
import com.aad.ws.domain.UserTest;
import com.aad.ws.dto.TestsCollection;
import com.aad.ws.dto.UserTestCollection;

public class TestsService {

	@Autowired
	private TestsDAO testsDao;
	private static final Logger logger = Logger.getLogger(TestsService.class);
	
	public TestsCollection getTests(long id) {
		logger.debug("id :" + id);
		List<Test> testsDomain = testsDao.getTestsForId(id);
		logger.debug("testsDomain :" + testsDomain);
		TestsCollection testCol = covertTestDomainToDto(testsDomain);
		logger.debug("testCol :" + testCol);
		return testCol;
	}

	private TestsCollection covertTestDomainToDto(List<Test> testsDomain) {
		if (testsDomain == null)
			return null;
		TestsCollection col = new TestsCollection();
		List<com.aad.ws.dto.Test> tests = new ArrayList<com.aad.ws.dto.Test>(); 
		for(Test testDomain: testsDomain){
			com.aad.ws.dto.Test testDto = new com.aad.ws.dto.Test();
			testDto.setTestId(testDomain.getTestId());
			testDto.setTestName(testDomain.getTestName());
			tests.add(testDto);
		}
		col.setTest(tests);
		return col;
	}

	public UserTestCollection getTestDetails(long id) {
		logger.debug("id :" + id);
		List<UserTest> userTestsDomain = testsDao.getUserTestsForId(id);
		logger.debug("userTests Domain Object :" + userTestsDomain);
		UserTestCollection userTestCol = covertUserTestDomainToDto(userTestsDomain);
		logger.debug("userTests DTO Object :" + userTestCol);
		return userTestCol;
	}

	private UserTestCollection covertUserTestDomainToDto(
			List<UserTest> userTestsDomain) {
		if (userTestsDomain == null)
			return null;
		UserTestCollection col = new UserTestCollection();
		List<com.aad.ws.dto.UserTest> tests = new ArrayList<com.aad.ws.dto.UserTest>(); 
		
		for(UserTest useDomain : userTestsDomain){
			com.aad.ws.dto.UserTest userTestDto = new com.aad.ws.dto.UserTest();
			userTestDto.setTestId(useDomain.getTestId());
			userTestDto.setTime(useDomain.getTime());
			userTestDto.setQuesAttented(useDomain.getQuesAttented());
			userTestDto.setScore(useDomain.getScore());
			tests.add(userTestDto);
		}
		col.setUserTests(tests);
		return col;
	}


}
