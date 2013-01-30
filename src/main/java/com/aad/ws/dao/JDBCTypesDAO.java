package com.aad.ws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.aad.ws.dto.Type;

public class JDBCTypesDAO implements TypesDAO {

	private static final String SELECT_TYPES_QUERY = "select * from app_type";

	private static final Logger logger = Logger.getLogger(JDBCTypesDAO.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Type> getTypes() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		List<Type> types = this.jdbcTemplate.query(
				SELECT_TYPES_QUERY, parameters,
				new TypesRowMapper());
		return types;
	}
	
	class TypesRowMapper implements RowMapper<Type> {
		public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
			Type type = new Type();
			type.setTypeId(rs.getInt("app_type_id"));
			type.setAppType(rs.getString("app_type"));
			type.setAppExtention(rs.getString("app_extension"));
			return type;
		}
	}
	

}
