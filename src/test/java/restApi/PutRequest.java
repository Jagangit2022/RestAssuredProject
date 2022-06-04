package restApi;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {

	
	@Test
	public void test1() {
		RestAssured.baseURI="http://localhost:3000/employees/";
		
		Map<String,Object> MapObj = new HashMap<String,Object>();
		
		//JSONObject jobj=new JSONObject();
		MapObj.put("name", "Alex");
		MapObj.put("salary", "6500");
		
		RequestSpecification request = RestAssured.given();
		Response response =	request.contentType(ContentType.JSON).accept(ContentType.JSON).body(MapObj).put("/6");

		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
	
		int ResponseCode = response.getStatusCode();
		AssertJUnit.assertEquals(ResponseCode, 200);
		
		JsonPath jpath = response.jsonPath();

		System.out.println("ID : "+	jpath.get("id"));
	}
}
