package com.aad.ws.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.CategoryDAO;
import com.aad.ws.domain.Category;
import com.aad.ws.dto.CategoryCollection;

public class CategoryService {

	@Autowired
	private CategoryDAO categoryDao;

	private static final Logger logger = Logger.getLogger(CategoryService.class);
	
	public CategoryCollection getCategories() {
		logger.debug(">> getCategories");
		List<Category> categories = categoryDao.getCategories();
		CategoryCollection categoryCol = new CategoryCollection();
		categoryCol.setCategories(categories);
		logger.debug("CategoryCollection :" + categoryCol);
		return categoryCol;
	}
}
