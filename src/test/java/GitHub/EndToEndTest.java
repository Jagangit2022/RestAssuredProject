package GitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	@Test
	public void test1() {
		RestAssured.baseURI="https://api.github.com/users/jagangit2022/repos";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
		
		int ResponseCode = response.getStatusCode();
		AssertJUnit.assertEquals(ResponseCode, 200);

	}
	
	@Test
	public void test2() throws IOException {
		RestAssured.baseURI="https://api.github.com/user/repos";
		

		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));

		
		RequestSpecification request = RestAssured.given();
		Response response =	request.auth().oauth2("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").contentType(ContentType.JSON).accept(ContentType.JSON).body(dataBytes).post();

		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
	
		int ResponseCode = response.getStatusCode();
		AssertJUnit.assertEquals(ResponseCode, 201);
		
		//JsonPath jpath = response.jsonPath();

		//System.out.println("ID : "+	jpath.get("id"));

	}

}
