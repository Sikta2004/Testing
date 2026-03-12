package Shopper;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Dynamic_initialization {

	String shopperId, jwtToken;
	@Test
	public void loginTest() {
		HashMap map = new HashMap<>();
		map.put("email", "siktadas1133@gmail.com");
		map.put("password", "Sikta@1234");
		map.put("role", "SHOPPER");
		
		Response res = given().relaxedHTTPSValidation()
		.contentType("application/json")
		.body(map)
		.when().post("https://www.shoppersstack.com/shopping/users/login");

		shopperId = res.jsonPath().getString("data.userId");
		System.out.println(shopperId);
		
		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println(jwtToken);
		
	}	
	
	
	@Test(dependsOnMethods ="loginTest")
	public void fetchData() {
		Response res=given().relaxedHTTPSValidation().contentType("application/json")
		.auth().oauth2(jwtToken)
		.pathParam("shopperID", shopperId)
		.baseUri("https://www.shoppersstack.com/shopping")
		.when().get("/shoppers/{shopperID}");
        System.out.println(res.prettyPrint());
      }
	
}

