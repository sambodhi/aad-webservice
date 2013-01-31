package com.aad.ws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aad.ws.domain.Results;
import com.aad.ws.dto.Question;
import com.aad.ws.dto.QuestionTest;
import com.aad.ws.dto.UTest;
import com.aad.ws.dto.UserQuestionTest;
import com.aad.ws.dto.UserSession;
import com.aad.ws.dto.subUserTest;


public class JDBCResultDAO implements ResultDAO{
	
	private static final Logger logger = Logger.getLogger(JDBCResultDAO.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	private static final String INSERT_USER_TEST="insert into user_test"
			+"(test_id,user_session_id,score,out_of,time,ques_attended,difficulty,start_time) values (:TEST_ID,:USER_SESSION_ID,:SCORE,:OUT_OF,:TIME,:QUES_ATTENDED,:DIFFICULTY,:START_TIME)";
	private static final String INSERT_TEST="insert into test"
			+"(test_id,app_id,total_questions,test_name) values (:TEST_ID,:APP_ID,:TOTAL_QUESTIONS,:TEST_NAME)";
	private static final String INSERT_QUESTION="insert into question"
			+"(question_id,correct_answer,type) values (:QUESTION_ID,:CORRECT_ANSWER,:TYPE)";
	private static final String INSERT_QUESTION_TEST="insert into question_test"
			+"(test_id,question_id) values (:TEST_ID,:QUESTION_ID)";
	private static final String INSERT_USER_QUESTION_TEST="insert into user_question_test"
			+"(test_id,user_session_id,question_id,time,number_of_clicks,final_answer,mark) values(:TEST_ID,:USER_SESSION_ID,:QUESTION_ID,:TIME,:NUMBER_OF_CLICKS,:FINAL_ANSWER,:MARK)";
	private static final String CREATE_USER_SESSION_QUERY = "insert into user_session"
	+"(user_session_id) values (:userSessionId)";
	private static final String TEST_ID_EXIST= "select count(0) from test where test_id=:TEST_ID ";
	private static final String QUESTION_ID_EXIST= "select count(0) from question where question_id=:QUESTION_ID ";
	
	public UserSession createUserSession(UserSession user) {
		
		user.setUserSessionId(0);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
//		java.sql.Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
//		user.setTimestamp(timestamp);
		
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				user);
		try {
			this.jdbcTemplate.update(CREATE_USER_SESSION_QUERY, namedParameters,
					keyHolder);
			if (keyHolder.getKey() != null) {
				int id = keyHolder.getKey().intValue();
				user.setUserSessionId(id);
				return user;
			}
		} catch (Throwable e) {
			System.out.println("createApplication: error in creating new application" + e);
		}
		System.out.println("*******************user session*********************************");
		System.out.println(user.toString());
		return user;
	}

	
	public UTest createTest(UTest test) {
		
		SqlParameterSource namedParametersForTest = new BeanPropertySqlParameterSource(
				test);
		int numTest = 0;
		//TODO: remove harcoding
		test.setTEST_NAME("abccc");
		try {
			numTest = this.jdbcTemplate.update(
					INSERT_TEST, namedParametersForTest);
		} catch (Throwable e) {
			logger.error("", e);
		}
		
		logger.debug("numTest:: "+ numTest);
		return test;
		
	}

	
	
	public subUserTest createUserTest(subUserTest usertest) {
		
		SqlParameterSource namedParametersForUserTest = new BeanPropertySqlParameterSource(
				usertest);
		int updatedUserTest = 0;
		try {
			updatedUserTest = this.jdbcTemplate.update(
					INSERT_USER_TEST, namedParametersForUserTest);
		} catch (Throwable e) {
			logger.error("", e);
		}
		logger.debug("updatedUserTest:: "+ updatedUserTest);
		return usertest;
		
	}

	public boolean TestIdExist (UTest utest) {
		
		SqlParameterSource namedParametersForTestIdExist = new BeanPropertySqlParameterSource(
				utest);
		boolean Testidno = false;
		if (jdbcTemplate.queryForInt(TEST_ID_EXIST, namedParametersForTestIdExist)!=0)
		{ 
			Testidno=true;
		}
		return Testidno;
		
	}	
	
	public boolean QuestoinIdExist (Question question) {
		
		SqlParameterSource namedParametersForQuestionIdExist = new BeanPropertySqlParameterSource(
				question);
		boolean questionidno = false;
		if (jdbcTemplate.queryForInt(QUESTION_ID_EXIST, namedParametersForQuestionIdExist)!=0)
		{ 
			questionidno=true;
		}
		return questionidno;		
	}	
	
	
	
	
	public Results submitResult(Results res) {
		
		logger.debug("Result :" + res);
		
		// create test
		logger.debug("BEGIN : Inserting Test");
		UTest test=new UTest();
		test.setTEST(res);
		if(TestIdExist(test) == false){
			createTest(test);
		}

		logger.debug("END : Inserting Test");
		logger.debug("test :" + test);
		
		//create user_session
		logger.debug("Inserting User Session");
		//create user_session
		UserSession user=new UserSession();
		user=createUserSession(user);
				
		if(user == null){
			logger.error("User session couldnt be interted. Inserting User Session gave error!!!!!!!!!!");
			return null;
		}
				
		long sessionId=user.getUserSessionId();
		
		logger.debug("END : Inserting User Session");
		logger.debug("sessionId :" + sessionId);		
		
		
		
		
		
		
		
		//create question test, question, user question
		List<QuestionTest> questiontest= new ArrayList<QuestionTest>();
		List<Question> question= new ArrayList<Question>();
		List<UserQuestionTest> userquestiontest= new ArrayList<UserQuestionTest>();
		SqlParameterSource namedParametersForQuestionTest;
		SqlParameterSource namedParametersForQuestion;
		SqlParameterSource namedParametersForUserQuestionTest;
		
		for(int i=0; i<res.getquestions().size();i++)
		{	
			question.add(new Question(res.getquestions().get(i)));
			questiontest.add(new QuestionTest(res,res.getquestions().get(i)));
			
			Question q1 = question.get(i);
			
			if(QuestoinIdExist(q1) == false){
				logger.debug("Inserting Question -- " + q1);
				namedParametersForQuestion = new  BeanPropertySqlParameterSource(q1);
				try {
					int numQuestion = this.jdbcTemplate.update(
							INSERT_QUESTION, namedParametersForQuestion);
					logger.debug("numQuestion ::" + numQuestion);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				} catch (Throwable e) {
				}
			}
			
			
			QuestionTest qt = questiontest.get(i);
			logger.debug("Inserting QuestionTest -- " + qt);
			namedParametersForQuestionTest = new BeanPropertySqlParameterSource(questiontest.get(i));
			try {
				int numQuestionTest = this.jdbcTemplate.update(
						INSERT_QUESTION_TEST, namedParametersForQuestionTest);
				logger.debug("numQuestionTest ::" + numQuestionTest);
				System.out.println("**************************************************");
				System.out.println(questiontest.get(i).toString());
				System.out.println("**************************************************");
			} catch (Throwable e) {
			}
		}
		
		//create user_test
				logger.debug("BEGIN : Inserting User Test");
				subUserTest usertest= new subUserTest(res, sessionId);
				createUserTest(usertest);
				logger.debug("END : Inserting User Test");
		
		     // loop to find all the questions
		for(int i=0; i<res.getquestions().size();i++)
		{	
			
			
			userquestiontest.add(new UserQuestionTest(res,res.getquestions().get(i), sessionId));
			
			UserQuestionTest uqt = userquestiontest.get(i);
			logger.debug("Inserting UserQuestionTest -- " + uqt);
			namedParametersForUserQuestionTest = new BeanPropertySqlParameterSource(uqt);
			try {
				int numUserQuestionTest = this.jdbcTemplate.update(
						INSERT_USER_QUESTION_TEST, namedParametersForUserQuestionTest);
				logger.debug("numUserQuestionTest ::" + numUserQuestionTest);
			} catch (Throwable e) {
			}
			
			
		}
		
		return res;
	}
}
