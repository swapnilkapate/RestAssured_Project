package com.api.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	// common things for every test case
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String userid="2";
	
	
}
