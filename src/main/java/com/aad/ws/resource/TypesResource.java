package com.aad.ws.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aad.ws.dto.TypesCollection;
import com.aad.ws.service.TypesService;

@Component
@Path("/types")
public class TypesResource {

	@Autowired
	private TypesService typesService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TypesCollection getTypes() {
		
		TypesCollection types = typesService.getTypes();
//		
//		TypesCollection types = new TypesCollection();
//		
//		Type type1 = new Type();
//		type1.setAppExtention("jar");
//		type1.setAppType("jar");
//		type1.setTypeId(1);
//		
//		Type type2 = new Type();
//		type2.setAppExtention("text");
//		type2.setAppType("text");
//		type2.setTypeId(2);
//		
//		List<Type> typeList = new ArrayList<Type>();
//		typeList.add(type1);
//		typeList.add(type2);
//		
//		types.setTypes(typeList);
		
		return types; 
	}
}
