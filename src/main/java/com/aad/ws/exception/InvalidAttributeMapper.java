package com.aad.ws.exception;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;


@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvalidAttributeMapper implements ExceptionMapper<com.aad.ws.exception.InvalidAttribute>{
	
	public InvalidAttributeMapper() {
		super();
	}
	
	public Response toResponse(InvalidAttribute e) {
		return Response.status(e.getErrorCode()).entity(new ExceptionInfo(e.getErrorCode(), e.getErrorAttribute(), e.getMessage())).type(MediaType.APPLICATION_JSON).build();
	}
	
}
