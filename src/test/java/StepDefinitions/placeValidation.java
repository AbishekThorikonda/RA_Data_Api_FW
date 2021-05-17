package StepDefinitions;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.location;
import Resources.AddPlaceTestData;
import Resources.ApiResources;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class placeValidation extends Utils{
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response; 
	static String place_id;
	@Given("Add place payload")
	public void add_place_payload() throws IOException {
	 AddPlaceTestData testData = new AddPlaceTestData();
      res = given().spec(requestSpecification()).body(testData.addPlacePayload());  
 
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String endpoint, String method) {
		ApiResources resourceApi=ApiResources.valueOf(endpoint);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceApi.getResource()).then().spec(resSpec).extract().response();
		else if(method.equalsIgnoreCase("GET"))
		response = res.when().get(resourceApi.getResource());
		}
	@Then("the api call is succesful with status code {int}")
	public void the_api_call_is_succesful_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in responsebody is {string}")
	public void in_responsebody_is(String key, String value) {
		 System.out.println("yo-1");
		 assertEquals(getJsonPath(response, key),value);
	}
	
			
	@Then("verify placeid created maps to {string} using {string}")
	public void verify_placeid_created_maps_to_using(String actualname, String endpoint) throws IOException {
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(endpoint,"GET");
		String name = getJsonPath(response,"name");
		assertEquals(name,actualname);
			}

	@Given("DeletePlaceApi functionality")
	public void delete_place_api_functionality() throws IOException {
			
	res=given().spec(requestSpecification()).body(DeletePayload(place_id));
	System.out.println("Delete");
	}









}
