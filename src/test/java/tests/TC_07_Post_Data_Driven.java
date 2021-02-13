package tests;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_07_Post_Data_Driven {

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "data_Provider")
	public void test_Data_Driven(String name, String salary, String age) {

		// Specify Base URL
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Request payload sending along with the post request
		JSONObject reqParams = new JSONObject();

		reqParams.put("name", name);
		reqParams.put("salary", salary);
		reqParams.put("age", age);

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

	@SuppressWarnings("resource")
	@DataProvider(name = "data_Provider")
	public String[][] getEmpData() throws IOException {

		FileInputStream fis = new FileInputStream("src\\main\\java\\test_Data\\Data_Driven_RestAssured.xlsx");

		XSSFWorkbook wbk = new XSSFWorkbook(fis);
		XSSFSheet sht = wbk.getSheetAt(0);

		int rowCount = sht.getPhysicalNumberOfRows();
		int cellCount = sht.getRow(0).getLastCellNum();
		String empData[][] = new String[rowCount - 1][cellCount];

		for (int i = 1; i <= rowCount - 1; i++) {

			for (int j = 0; j <= cellCount - 1; j++) {
				empData[i - 1][j] = sht.getRow(i).getCell(j).toString();
			}
		}

		return empData;
	}
}
