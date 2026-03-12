package Shopper;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
//import io.restassured.RestAssured;

public class ShopperLoginTest {

	@Test
	public void loginTest() {
		
		//RestAssured.
		given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body("{\r\n"
				+ "  \"email\": \"siktadas1133@gmail.com\",\r\n"
				+ "  \"password\": \"Sikta@1234\",\r\n"
				+ "  \"role\": \"SHOPPER\"\r\n"
				+ "}")
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login")
		
		.then()
		.assertThat().statusCode(200)
		
		.log().all();
	}
	
	@Test
	public void fetchData() {
		
		
		given()
		.contentType("application/json")
		.auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaWt0YWRhczExMzNAZ21haWwuY29tIFNIT1BQRVIiLCJleHAiOjE3NzMyNDAyOTcsImlhdCI6MTc3MzIwNDI5N30.6eZwnArNGyQHy1-B21qapZiRcDhclNTsrRzQmJgYC8c")
		.pathParam("shopper_ID", "363299")
		.relaxedHTTPSValidation()
		
		.when()
		.get("https://www.shoppersstack.com/shopping/shoppers/{shopper_ID}")
		
		.then()
		.assertThat().statusCode(200)
		.log().all();
	}
}
