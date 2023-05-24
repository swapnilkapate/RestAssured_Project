package com.api.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String username() {
		String name = RandomStringUtils.randomAlphabetic(1); // create random alphabet of 1 character
		return("john"+name);
	}
	
	public static String job() {
		String job = RandomStringUtils.randomAlphabetic(5); // create random alphabet of 5 character
		return job;
	}
	
	public static String id() {
		String id = RandomStringUtils.randomNumeric(2); // create random number of 2 digits
		return id;
	}
	
}
