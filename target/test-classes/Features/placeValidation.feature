Feature: Validating place Api



@AddPlace @Smoke
Scenario Outline: verify if place is succesifully being added using Addplace api
Given Add place payload
When user calls "AddPlaceApi" with "post" http request
Then the api call is succesful with status code 200
And "status" in responsebody is "OK"
And verify placeid created maps to "<name>" using "getPlaceApi"

Examples:
|name|
|Abishek|

@DeletePlace @Smoke
Scenario: Validating Deleteplace Api
Given DeletePlaceApi functionality
When user calls "DeletePlaceApi" with "post" http request
Then the api call is succesful with status code 200
And "status" in responsebody is "OK"