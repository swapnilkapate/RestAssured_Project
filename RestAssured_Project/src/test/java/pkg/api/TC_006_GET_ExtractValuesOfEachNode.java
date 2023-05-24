package pkg.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_006_GET_ExtractValuesOfEachNode {
	
	@Test
	public void getUser() {
		
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response object
		Response response = httpRequest.request(Method.GET,"/users/2");
		
		//Print response in console
		String resBody = response.getBody().asString();
		System.out.println(resBody);
		
		//to get values of each node in response
		// use jsonpath for getting values of each node in json response
		JsonPath jsonpath = response.jsonPath();
		
		System.out.println(jsonpath.get("id"));
		System.out.println(jsonpath.get("email"));
		System.out.println(jsonpath.get("first_name"));
		System.out.println(jsonpath.get("last_name"));
		System.out.println(jsonpath.get());
		Assert.assertEquals(resBody.contains("id"), true);
		
		
	}
		
}
