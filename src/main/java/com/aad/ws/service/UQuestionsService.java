package com.aad.ws.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.UQuestionsDAO;
import com.aad.ws.domain.UQuestion;
import com.aad.ws.domain.UserUQuestion;
import com.aad.ws.dto.UQuestionsCollection;
import com.aad.ws.dto.UserUQuestionsCollection;


public class UQuestionsService {

	@Autowired
	private UQuestionsDAO questionsDao;
	private static final Logger logger = Logger.getLogger(UQuestionsService.class);
	
	public UQuestionsCollection getQuestions(long id) {
		logger.debug("id :" + id);
		List<UQuestion> questionsDomain = questionsDao.getUQuestionsForId(id);
		logger.debug("questionsDomain :" + questionsDomain);
		
		if( questionsDomain == null)
			return null;
		
		UQuestionsCollection col = new UQuestionsCollection();
		col.setUQuestion(questionsDomain);
		
		return col;
	}

	public UserUQuestionsCollection getUserUQuestions(long id){
		logger.debug("id :" + id);
		List<UserUQuestion> userQuestionsDomain = questionsDao.getUserUQuestionsForId(id);
		logger.debug("userTests Domain Object :" + userQuestionsDomain);
		
		if( userQuestionsDomain == null)
			return null;
		
		UserUQuestionsCollection col = new UserUQuestionsCollection();
		col.setUserUQuestion(userQuestionsDomain);
		return col;
	}
}
