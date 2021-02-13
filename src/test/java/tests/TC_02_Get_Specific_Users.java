package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_02_Get_Specific_Users {

	@Test
	public void Test_Specific_User() {

		RestAssured.baseURI = "https://reqres.in/api/users";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/2");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is :" + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

}
