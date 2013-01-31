package com.aad.ws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.aad.ws.domain.Test;
import com.aad.ws.domain.UserTest;

public class JDBCTestsDAO implements TestsDAO{

	private static final String SELECT_TESTS_QUERY = "select test_id, test_name from test where app_id=:appId";

	private static final String SELECT_USER_TESTS_QUERY = "select test_id, ques_attended, score, time from user_test where test_id=:testId ";
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Test> getTestsForId(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("appId", id);
		List<Test> tests = this.jdbcTemplate.query(
				SELECT_TESTS_QUERY, parameters,
				new TestsRowMapper());
		return tests;
	}

	class TestsRowMapper implements RowMapper<Test> {
		public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
			Test test = new Test();
			test.setTestId(rs.getLong("test_id"));
			test.setTestName(rs.getString("test_name"));
			return test;
		}
	}
	
	public List<UserTest> getUserTestsForId(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("testId", id);
		List<UserTest> tests = this.jdbcTemplate.query(
				SELECT_USER_TESTS_QUERY, parameters,
				new UserTestsRowMapper());
		return tests;
	}
	
	class UserTestsRowMapper implements RowMapper<UserTest> {
		public UserTest mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserTest test = new UserTest();
			test.setTestId(rs.getLong("test_id"));
			test.setScore(rs.getDouble("score"));
			test.setQuesAttented(rs.getInt("ques_attended"));
			test.setTime(rs.getDouble("time"));
			return test;
		}
	}
}
