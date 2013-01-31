package com.aad.ws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.aad.ws.domain.Application;

public class JDBCApplicationDAO implements
		ApplicationDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String CREATE_APPLICATION_QUERY = "insert into application"
			+ "(app_categ_id,app_type_id,app_name,app_size,developer_name,description,app_url,icon_url) values(:appCategId,:appTypeId,:appName,:appSize,:developrName,:description,:url,:iconUrl)";

//	private static final String CREATE_APPLICATION_QUERY = "insert into application"
//		+ "(app_categ_id,app_type_id,app_name,app_size,developer_name,description,app_url) values(:appCategId,:appTypeId,:appName,:appSize,:developrName,:description,:url)";

	private static final String UPDATE_APPLICATION_QUERY = "update application set app_categ_id=:appCategId,app_type_id=:appTypeId where app_id=:appId";
	private static final String SELECT_APPLICATION_QUERY = "select * from application where app_id=:appId";
	
	private static final String SELECT_APPLICATION_FOR_CATEGORY_QUERY = "select * from application where app_categ_id=:categId";

	private static final Logger logger = Logger.getLogger(JDBCApplicationDAO.class);
	
	public Application createApplication(Application application) {
		logger.debug("JDBCApplicationDAO >> createApplication >> application :" + application);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				application);
		try {

			this.jdbcTemplate.update(CREATE_APPLICATION_QUERY, namedParameters,
					keyHolder);
			if (keyHolder.getKey() != null) {
				int id = keyHolder.getKey().intValue();
				logger.debug("Generated id:" + id);
				application.setAppId(id);
				return application;
			}
		} catch (Throwable e) {
			logger.error("createApplication: error in creating new application" + e.getMessage(), e);
		}
		return null;
	}

	public List<Application> getApplication(Application value) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("appId", value.getAppId());
		List<Application> applicationList = this.jdbcTemplate.query(
				SELECT_APPLICATION_QUERY, parameters,
				new ApplicationRowMapper());
		return applicationList;
	}

	public Application updateApplication(Application application) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				application);
		try {
			int noOfRowsUpdated = this.jdbcTemplate.update(
					UPDATE_APPLICATION_QUERY, namedParameters);
			// if (noOfRowsUpdated > 0)
			// update successful
		} catch (Throwable e) {
			logger.error("updateApplication: error in updating application" + e.getMessage(), e);
		}
		return null;
	}

	
	class ApplicationRowMapper implements RowMapper<Application> {
		public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
			Application application = new Application();
			application.setAppId(rs.getLong("app_id"));
			application.setAppCategId(rs.getLong("app_categ_id"));
			application.setAppName(rs.getString("app_name"));
			application.setAppTypeId(rs.getLong("app_type_id"));
			application.setDescription(rs.getString("description"));
			application.setUrl(rs.getString("app_url"));
			application.setAppSize(rs.getString("app_size"));
			application.setIconUrl(rs.getString("icon_url"));
			application.setDeveloprName(rs.getString("developer_name"));
			return application;
		}
	}

	public List<Application> getApplicationForCategory(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("categId", id);
		List<Application> applicationList = this.jdbcTemplate.query(
				SELECT_APPLICATION_FOR_CATEGORY_QUERY, parameters,
				new ApplicationRowMapper());
		return applicationList;
	}

}
