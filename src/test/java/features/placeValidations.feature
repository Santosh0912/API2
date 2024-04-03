Feature: Validating place APIs

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add place Payload with "<name>","<language>","<address>"
    When user calls  "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" the in response body is "OK"
    And "scope" in response_body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name    | language | address            |
      | AAHouse | English  | World Cross Centre |

  @DeletePlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add place Payload with "<name>","<language>","<address>"
    When user calls  "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200

    Examples: 
      | name          | language | address  |
      | SanthoshHouse | Telugu   | Tajmahal |
