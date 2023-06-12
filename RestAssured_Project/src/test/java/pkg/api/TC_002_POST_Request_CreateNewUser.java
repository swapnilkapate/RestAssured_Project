package pkg.api;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002_POST_Request_CreateNewUser {
	
	@Test
	void registerUser() {
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Request object
		RequestSpecification httprequest = RestAssured.given();
		
		// Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
	    
	    requestParams.put("name", "morpheus");
		requestParams.put("job", "leader");
		
		//Add header String in the request body is a JSON
		httprequest.header("Content-Type","application/json");
		
		httprequest.body(requestParams.toJSONString()); // attach above data to request
		
		// Response object and sending the post request
		Response response = httprequest.request(Method.POST,"/users");
			//OR
		//Response response = httprequest.post("/users");
		
		// Print response in console
		String resbody = response.getBody().asString();
		System.out.println("Response body is : "+resbody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code : "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// verify id is present in response
		String id = response.jsonPath().get("id");
		System.out.println(id);
		
		System.out.println(response.asPrettyString());
		
	}	
	
}
