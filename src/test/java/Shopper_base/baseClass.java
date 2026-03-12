package Shopper_base;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class baseClass {

	public static String shopperId;
	public static String jwtToken;
	public static int productId=52;
	public static int quantity;
	public static int itemId;
	
	@BeforeClass
	public void login() {
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

}

