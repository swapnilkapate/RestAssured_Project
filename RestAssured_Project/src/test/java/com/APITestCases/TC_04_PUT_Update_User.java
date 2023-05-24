package com.APITestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_04_PUT_Update_User extends TestBase {
	
	String username = RestUtils.username();
	String job = RestUtils.job();
	
	@BeforeClass
	void updateUser() {
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Request Object
		httpRequest = RestAssured.given();
		
		// Request payload
		JSONObject reqParams = new JSONObject();
		
		reqParams.put("username", username);
		reqParams.put("job", job);
		
		// Add body payload to request as header
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(reqParams.toJSONString()); // attached above data to request
		
		// Sending the PUT request and storing response in response object
		response = httpRequest.request(Method.PUT,"/users/2");
	}
	
	@Test
	void checkResponseBody() {
		String resbody = response.getBody().asString();
		System.out.println("Response body: "+resbody);
		Assert.assertEquals(resbody.contains(username), true);
		Assert.assertEquals(resbody.contains(job), true);
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		String statusLine = response.statusLine();
		System.out.println("Status Line: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkResponseTime() {
		
		long responseTime = response.getTime();
		System.out.println("Response Time: "+responseTime);
		if(responseTime>2000)
			System.out.println("Response time is greater than 2000");
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test
	void checkContentType() {
		// capture details of specific headers from response
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type: "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
}
