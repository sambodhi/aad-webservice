package com.aad.ws.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.domain.Category;
import com.aad.ws.dto.CategoryCollection;
import com.aad.ws.service.CategoryService;

@Component
@Path("/categories")
public class CategoriesResource {

	@Autowired
	private CategoryService categoryService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CategoryCollection getCategories() {
		//TODO: uncomment when database is reachable
		CategoryCollection col = categoryService.getCategories(); 
		
		//remove it!
//		Category cat1 = new Category();
//		cat1.setCategId(1212);
//		cat1.setCategType("Maths");
//		Category cat2 = new Category();
//		cat2.setCategId(321);
//		cat2.setCategType("Science");
//		List<Category> categories = new ArrayList<Category>();
//		categories.add(cat1); categories.add(cat2);
//		CategoryCollection col = new CategoryCollection();
//		col.setCategories(categories);
		
		return col;
	}
}
