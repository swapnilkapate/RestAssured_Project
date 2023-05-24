package pkg.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_005_ValidateJSONResponse {
	
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
		
		// validate json response
		Assert.assertEquals(resbody.contains("Janet"), true);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
}
