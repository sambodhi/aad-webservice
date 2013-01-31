package com.aad.ws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.aad.ws.domain.UQuestion;
//import com.aad.ws.domain.UserUQuestion;
import com.aad.ws.domain.UserUQuestion;

public class JDBCUQuestionsDAO implements UQuestionsDAO{

	private static final String SELECT_QUESTIONS_QUERY = "select question_id from question_test where test_id=:testId";

	private static final String SELECT_USER_QUESTIONS_QUERY = "select number_of_clicks, time from user_question_test where question_id=:questionId";
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<UQuestion> getUQuestionsForId(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("testId", id);
		List<UQuestion> tests = this.jdbcTemplate.query(
				SELECT_QUESTIONS_QUERY, parameters,
				new QuestionsRowMapper());
		return tests;
	}

	class QuestionsRowMapper implements RowMapper<UQuestion> {
		public UQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
			UQuestion question = new UQuestion();
			question.setUQuestionId(rs.getLong("question_id"));
		
			return question;
		}
	}
	
	public List<UserUQuestion> getUserUQuestionsForId(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("questionId", id);
		List<UserUQuestion> questionsStats= this.jdbcTemplate.query(
				SELECT_USER_QUESTIONS_QUERY, parameters,
				new UserQuestionsRowMapper());
		return questionsStats;
	}
	
	class UserQuestionsRowMapper implements RowMapper<UserUQuestion> {
		public UserUQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserUQuestion question = new UserUQuestion();
			question.setNumberOfClicks(rs.getInt("number_of_clicks"));
			question.setTime(rs.getInt("time"));
			return question;
		}
	}
	
}
