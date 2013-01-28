package com.aad.ws.resource;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.jayway.restassured.RestAssured;
import com.aad.ws.resource.IntegrationTest;

@Category(IntegrationTest.class)
public class AppResourceTest {

	static{
		RestAssured.port = 9999;
	}
	
	/*@Test
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
	}*/
	
	@Test
	public void testStoreApp() {
		System.out.println("*********************** Integration test ***********************");
		given().
			multiPart(new File("jhall.jar")).
			parameters("name", "mathsapp1", "description", "maths sample app", "type", "1", "category", "1", "size", "500 KB").
		expect().
			statusCode(201).
		when().
			post("/aad-ws/api/application/upload");
	}
	
	
	@Test
	public void testGetApplicationDetails() {
		given().
			contentType("application/json; charset=UTF-16");
		expect().
			statusCode(200).
		when().
			get("aad-ws/api/application/1");
	}
}
