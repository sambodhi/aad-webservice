package com.aad.ws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.aad.ws.domain.Category;

public class JDBCCategoryDAO implements CategoryDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SELECT_CATEGORY_QUERY = "select * from app_category where app_categ_id=:categId";

	private static final String SELECT_ALL_CATEGORY = "select * from app_category";
	
	public Category getCategory(long id) {
		Category category = null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("categId", id);
		List<Category> categories = this.jdbcTemplate.query(
				SELECT_CATEGORY_QUERY, parameters,
				new CategoryRowMapper());
		if(categories != null && categories.size() > 0) {
			category = categories.get(0);
		}
		return category;
	}

	class CategoryRowMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setCategId(rs.getLong("app_categ_id"));
			category.setCategType(rs.getString("app_categ_type"));
			return category;
		}
	}

	public List<Category> getCategories() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		List<Category> categories = this.jdbcTemplate.query(
				SELECT_ALL_CATEGORY, parameters,
				new CategoryRowMapper());
		return categories;
	}
}
