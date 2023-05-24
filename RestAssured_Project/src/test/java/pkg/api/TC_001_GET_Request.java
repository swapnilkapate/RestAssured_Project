package pkg.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_001_GET_Request {
	
	@Test
	void getUserDetails() {
		//Specify base URI
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		// Request object
		RequestSpecification httprequest = RestAssured.given();
		
		// Response object
		Response response = httprequest.request(Method.GET,"/users/1105");
		
		// Print response in console
		String resbody = response.getBody().asString();
		System.out.println("Response body is : "+resbody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status Line - "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	
}
