package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		placeValidation pv = new placeValidation();
		
		if(pv.place_id ==null)
		{
		pv.add_place_payload();
		pv.user_calls_with_http_request("AddPlaceApi","POST");
		pv.verify_placeid_created_maps_to_using("Abishek","getPlaceApi");
	}
}
}