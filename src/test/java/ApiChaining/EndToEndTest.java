package ApiChaining;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	Response response;
	String BaseURI = "http://localhost:3000/employees/";
	
	@Test
	public void test1() {
	
			
		response=GetMethodAll();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		response=PostMethod("Luchia","2000");
		AssertJUnit.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath = response.jsonPath();
		int EmpId=jpath.get("id");
		System.out.println("ID : "+	EmpId);

		
		response=PutMethod(EmpId,"Rick","6000");
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		String EmpName=jpath.get("name");
		System.out.println("Emp Name : "+	EmpName);
		AssertJUnit.assertEquals(EmpName,"Rick");
		
		response=DeleteMethod(EmpId);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		AssertJUnit.assertEquals(response.body().asString(), "{}");
		
		response=GetMethod(EmpId);
		AssertJUnit.assertEquals(response.getStatusCode(), 404);
		AssertJUnit.assertEquals(response.body().asString(), "{}");

	}
	
	public Response GetMethodAll() {
		RestAssured.baseURI=BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get();

		return response;
	}
	
	public Response PostMethod(String Name, String Salary) {
		RestAssured.baseURI=BaseURI;
		JSONObject jobj=new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		Response response =	request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString()).post("/create");


		return response;
	}
	
	public Response PutMethod(int EmpId, String Name, String Salary) {
		RestAssured.baseURI=BaseURI;
		JSONObject jobj=new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		Response response =	request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString()).put("/"+EmpId);

		return response;
	}

	public Response DeleteMethod(int EmpId) {
		RestAssured.baseURI=BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/"+EmpId);

		return response;
	}

	public Response GetMethod(int EmpId) {
		RestAssured.baseURI=BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/"+EmpId);

		return response;
	}

}
