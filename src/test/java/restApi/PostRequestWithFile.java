package restApi;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithFile {
	
	@Test
	public void test1() throws IOException {
		RestAssured.baseURI="http://localhost:3000/employees/";
		

		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));

		
		RequestSpecification request = RestAssured.given();
		Response response =	request.contentType(ContentType.JSON).accept(ContentType.JSON).body(dataBytes).post("/create");

		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
	
		int ResponseCode = response.getStatusCode();
		AssertJUnit.assertEquals(ResponseCode, 201);
		
		JsonPath jpath = response.jsonPath();

		System.out.println("ID : "+	jpath.get("id"));

	}
}
