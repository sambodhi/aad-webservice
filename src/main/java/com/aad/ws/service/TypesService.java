package com.aad.ws.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.TypesDAO;
import com.aad.ws.dto.Type;
import com.aad.ws.dto.TypesCollection;

public class TypesService {

	@Autowired
	private TypesDAO typesDao;
	private static final Logger logger = Logger.getLogger(TypesService.class);
	
	public TypesCollection getTypes() {
		TypesCollection col = new TypesCollection();
		List<Type> types = typesDao.getTypes();
		col.setTypes(types);
		return col;
	}

}
