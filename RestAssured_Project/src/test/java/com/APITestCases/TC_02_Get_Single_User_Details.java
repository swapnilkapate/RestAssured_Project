package com.APITestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_02_Get_Single_User_Details extends TestBase {
	
	@BeforeClass
	void getAllUsersDetails() {
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Request object
		httpRequest = RestAssured.given();
		
		// Response object
		response = httpRequest.request(Method.GET,"/users/"+userid);		
	}
		
	@Test
	void checkResponseBody() {
		// Print response in console
		String resbody = response.getBody().asString();
		System.out.println("Response body is : "+resbody);
		Assert.assertEquals(resbody.contains(userid), true);
	}
		
	@Test
	void checkStatusCode() {
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code : "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status Line - "+statusLine);
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
	
	@Test
	void checkContentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content-Encoding: "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
}
