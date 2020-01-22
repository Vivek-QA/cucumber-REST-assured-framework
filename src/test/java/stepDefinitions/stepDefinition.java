package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;

public class stepDefinition extends Utils{

    //Declaring res and respec globally
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;

    @Given("^Add Place Payload with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        TestDataBuild data = new TestDataBuild(); // Data comes from TestDataBuildClass
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
        //Generic Request Specification comes from UtilClass
    }

    @When("^user calls \"([^\"]*)\" with Post http request$")
    public void user_calls_something_with_post_http_request(String strArg1){
        response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();
    }

    @Then("^the API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code_200() {
    assertEquals(response.getStatusCode(),200);
    }

    @Then("^Verify PlaceId Created with Get http request$")
    public void verify_placeid_created_with_get_http_request()  {

    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String keyValue, String ExpectedValue) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(keyValue).toString(),ExpectedValue);
    }


}
