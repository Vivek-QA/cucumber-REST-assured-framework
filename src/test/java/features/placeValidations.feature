Feature: Validating Place API
  @AddPlace
  Scenario Outline: Verify id Place is added successfully
    Given Add Place Payload with "<name>","<language>","<address>"
    When user calls "AddPlaceAPI" with Post http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
    |name           |language|address|
    |Stark house|English |29, side layout, cohen 09|
    |Batman Cave|French  |34, Sea cross valley     |