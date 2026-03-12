package Shopper;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HashMap_store {

	@Test
	public void loginTest() {
		HashMap map = new HashMap<>();
		map.put("email", "siktadas1133@gmail.com");
		map.put("password", "Sikta@1234");
		map.put("role", "SHOPPER");
		given().contentType("application/json").relaxedHTTPSValidation()
		.body(map)

		.when().post("https://www.shoppersstack.com/shopping/users/login")

		.then()
		.assertThat().statusCode(200)
		.log().all();
	}	

}
