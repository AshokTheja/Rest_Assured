package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_03_Post_Create_User {

	@SuppressWarnings("unchecked")
	@Test
	public void test_Create_User() {

		// Specify Base URL
		RestAssured.baseURI = "https://reqres.in/api/users";
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Request payload sending along with the post request
		JSONObject reqParams = new JSONObject();

		reqParams.put("name", "Lanister");
		reqParams.put("job", "King");

		httpRequest.header("Content-Type", "application/json");
		// attaching above data to the request
		httpRequest.body(reqParams.toJSONString());

		// Response Object
		Response response = httpRequest.request(Method.POST);

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);

		// Status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

	}

}
