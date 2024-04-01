package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class stepDefinition {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();;
	static JsonPath js;
	static APIResources resourceAPI;
	static String PlaceID;
	String sta;
	String YAHO;

	Utils utils = new Utils();

	@Given("Add place Payload with {string},{string},{string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// data = new TestDataBuild();
		System.out.println("STEP 1");

		res = given().spec(utils.requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

	// Step 2 for 2 Scenarios
	@When("user calls  {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		System.out.println("STEP 2");

		resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().log().all().post(resourceAPI.getResource()).then().log().all().spec(resspec).extract()
				.response();

		if (method.equals("POST")) {
			response = res.when().log().all().get(resourceAPI.getResource());
			System.out.println(response.asString());
		} else if (method.equals("GET")) {
			response = res.when().log().all().get(resourceAPI.getResource());
			System.out.println(response.asString());
		} // else if (method.equals("Delete")) {
//			response = res.when().delete(resourceAPI.getResource());
//			System.out.println(response.asString());
//		}
		System.out.println("Step 2 URL pass");
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		System.out.println("STEP 3");
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), 200);
		System.out.println(response.asString());
		// System.out.println(js.getString("status"));

	}

	@Then("{string} the in response body is {string}")
	public void status_in_response_body_is_ok(String stabun, String value) {
		// String responseString = response.asString();
		System.out.println("STEP 4");
		System.out.println(response.asString());
		js = new JsonPath(response.asString());
		System.out.println("Status : " + js.getString("status"));
		System.out.println("Place Id : " + js.getString("place_id"));
		System.out.println("Scope : " + js.getString("scope"));
		System.out.println("Reference : " + js.getString("reference"));
		System.out.println("ID : " + js.getString("id"));

		sta = js.getString("status");

		System.out.println("STAAAAAAAAAAA : " + sta);
		assertEquals(sta, "OK");
	}

	@Then("{string} in response_body is {string}")
	public void in_response_body_is(String status, String responses) {
		System.out.println("STEP 6");

		PlaceID = js.getString("place_id");
		System.out.println("Place IDDDDDDDDD : " + PlaceID);

		String Scope = js.getString("scope");
		System.out.println("Scope: " + Scope);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using_get_place_api(String name, String method) throws IOException {
		System.out.println("STEP 7");
		res = given().spec(utils.requestSpecification()).queryParam("place_id", PlaceID);
		user_calls_with_http_request(method, "GET");

	}

	// 2nd Scenarios

	// Step 1
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		System.out.println("Delete Payload step 1");
		System.out.println("Delete Payload : " + PlaceID);
		res = given().spec(utils.requestSpecification()).body(data.deletePlacePayload(PlaceID));

	}

	@Then("the in delete response body is {string}")
	public void the_in_delete_response_body_is(String method) throws IOException {

		System.out.println("Place ID : " + PlaceID);
		res = given().log().all().spec(utils.requestSpecification()).body(data.deletePlacePayload(PlaceID));
		String deleteResponse = res.when().delete(resourceAPI.getResource()).then().log().all().spec(resspec).extract()
				.response().asString();
		System.out.println("DELETE RESPONSE :" + deleteResponse);
		JsonPath js3 = new JsonPath(deleteResponse);
		String abc = js3.getString("status");

		user_calls_with_http_request(method, "DELETE");

	}

	@Then("check {string} the {string} in responsebody {string}")
	public void check_the_status_in_responsebody(String resource, String StatusCode, String status) throws IOException {

//		resourceAPI = APIResources.valueOf(resource);
//		res = given().log().all().spec(utils.requestSpecification()).body(data.deletePlacePayload(PlaceID));
//		///resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		//String Deleteresponse = res.when().log().all().delete(resourceAPI.getResource()).then().log().all().spec(resspec).extract().response().asString();
//		Response resp = res.when().log().all().delete("/maps/api/place/delete/json").then().log().all().extract().response();
//		// APIResources resourceAPI2 = APIResources.valueOf(resource);
//		System.out.println("SANTHOSH 1");
//		System.out.println(resourceAPI.getResource());
//		System.out.println("SANTHOSH 2");
//		// System.out.println(response.asString());
//		System.out.println("Place ID : "+PlaceID);
//		System.out.println("STATUS Code : " + resp.getStatusCode());
//		System.out.println("STATUS response : " +resp.asString());
		
		
//************** 2nd Way	
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		
//		String body = "{\n" + 
//				"    \"place_id\":\""+PlaceID+"\"\n" + 
//				"}\n" + 
//				"";
//		
//		Response res = RestAssured.given().queryParam("key", "qaclick123").body(body).when().delete("/maps/api/place/delete/json").then().extract().response();
//		
//		System.out.println(res.statusCode());
//		System.out.println(res.asString());
//		
//
//		JsonPath js5=new JsonPath(res.asString());
//		String Status = js5.getString("status");
//		System.err.println("Status sssssssss: "+Status);
	
		
		
//************** 3rd Way		
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		
		String body = "{\n" + 
				"    \"place_id\":\""+PlaceID+"\"\n" + 
				"}\n" + 
				"";
//		
//		Response res = RestAssured.given().queryParam("key", "qaclick123").body(body).when().delete("/maps/api/place/delete/json").then().extract().response();
		
		
		Response res = RestAssured.given().spec(utils.requestSpecification()).body(body).when().delete("/maps/api/place/delete/json").then().extract().response();	
		//Response res = RestAssured.given().spec(utils.requestSpecification()).body(body).when().post(APIResources.deletePlaceAPI).then().extract().response();
		
		System.out.println(res.statusCode());
		System.out.println(res.asString());
		

		JsonPath js5=new JsonPath(res.asString());
		String Status = js5.getString("status");
		System.err.println("Status sssssssss: "+Status);
		
		
		

	}

}
