package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTestPostNewUser {

	@Test(dataProvider = "userDataProvider")
	public void postNewUser(String name, String job, String id) {
		
		// Specify Base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Request object
		RequestSpecification httpreq = RestAssured.given();
		
		// Request payload sending along with post request
		JSONObject reqparams = new JSONObject();
		
		reqparams.put("name", name);
		reqparams.put("job", job);
		reqparams.put("id", id);
		
		// Add this JSON body to header in request object
		httpreq.header("Content-Type", "application/json");
		
		// Add above JSON body data to request
		httpreq.body(reqparams.toString());
		
		// Response Object
		Response res = httpreq.request(Method.POST,"/users");
		
		// Print response in console
		String resbody = res.getBody().asString();
		System.out.println("Response body: "+resbody);
		
		// Status code validation
		int statusCode = res.getStatusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, 201);
				
	}
	
	@DataProvider(name="userDataProvider")
	String[][] getUserData() throws IOException {
		// Hard coding
//		String userData[][] = {{"Swapnil","Leader","56"},{"Omkar","Senior","60"},{"Vishal","Sales","66"}};
//		return userData;
		
		// Soft coding
		
		String path = System.getProperty("user.dir")+"\\Resources\\UserData.xlsx";
		
		XLUtility xlutil = new XLUtility(path);
		
		int totalrows = xlutil.getRowCount("NewData");  // give parameter sheetname. it returs total no of rows
		int totalcolumns = xlutil.getCellCount("NewData", 1); // it returns total no of columns
		
		String userData[][] = new String [totalrows][totalcolumns];
		
		for (int i=1; i<=totalrows; i++)
		{
			for (int j=0; j<totalcolumns; j++)
			{
				userData [i-1][j] = xlutil.getCellData("NewData", i, j);
			}
		}
		return userData;
		
	}
	
	
	
	
}
