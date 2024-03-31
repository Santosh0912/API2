package stepDefinition;
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


public class test{
	
	public static void main(String[] args) {
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	String body = "{\n" + 
			"    \"place_id\":\"048913350535494576cb82931e30fdc1\"\n" + 
			"}\n" + 
			"";
	
	Response res = RestAssured.given().queryParam("key", "qaclick123").body(body).when().delete("/maps/api/place/delete/json").then().extract().response();
	
	System.out.println(res.statusCode());
	System.out.println(res.asString());
}
}