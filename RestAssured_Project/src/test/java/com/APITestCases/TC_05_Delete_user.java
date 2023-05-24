package com.APITestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC_05_Delete_user extends TestBase{
		
	@BeforeClass
	void getAllUsersDetails() {
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Request object
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/users");
		
		// First get the jsonpath object instance from the response interface
		JsonPath jsonpathevaluator = response.jsonPath();
		
		// Print response in console
		String resbody = response.getBody().asString();
		System.out.println("Response body is : "+resbody);
		
		// Capture id
		String userid = jsonpathevaluator.get("x.data[0].id");
		System.out.println(userid);
		System.out.println(jsonpathevaluator.get("x.page"));
		
		// Response object
		response = httpRequest.request(Method.DELETE,"/users/"+userid);		
	}
		
	@Test
	void checkResponseBody() {
		// Print response in console
		String resbody = response.getBody().asString();
		System.out.println("Response body is : "+resbody);
	}
		
	@Test
	void checkStatusCode() {
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code : "+statusCode);
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	void checkStatusLine() {
		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status Line - "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}
	
	@Test
	void checkResponseTime() {
		
		long responseTime = response.getTime();
		System.out.println("Response Time: "+responseTime);
		if(responseTime>2000)
			System.out.println("Response time is greater than 2000");
		Assert.assertTrue(responseTime<2000);
	}
	
	
}
