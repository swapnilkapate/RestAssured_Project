package pkg.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_GET_Request_ValidateHeaders {
	
	@Test
	void singleUserDetails() {
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Request object
		RequestSpecification httprequest = RestAssured.given();
		
		// Response object
		Response response = httprequest.request(Method.GET,"/users/2");
		
		// Print response in console
		String resbody = response.getBody().asString();
		System.out.println("Response body is : "+resbody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println("Status Line: "+response.statusLine());
		
		// capture details of specific headers from response
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type: "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content-Encoding: "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
		// to get all headers with values
		Headers allHeaders = response.headers();
		
		for(Header header:allHeaders) {
			System.out.println(header.getName()+" - "+header.getValue());
		}
		
		System.out.println(response.time());
		System.out.println(response.getTime());
	}
	
	
	
	
}
