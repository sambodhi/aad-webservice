package com.aad.ws.resource;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.jayway.restassured.RestAssured;

@Category(IntegrationTest.class)
public class AppResourceTest {

	static{
		RestAssured.port = 9999;
	}
	
	@Test
	public void testGetAppDetails() {
		System.out.println("*********************** Integration test ***********************");
		RestAssured.port = 9999;
		given().
	       contentType("application/json; charset=UTF-16");
		expect().
			body("name", equalTo("survey")).
			body("size", equalTo("50 MB")).
		when().
			get("/aad-ws/api/application/details/getAppList");
	}
	
	
}
