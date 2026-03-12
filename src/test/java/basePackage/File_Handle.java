package basePackage;

import static io.restassured.RestAssured.given;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class File_Handle extends baseClass  {
	int productId;
	int quantity;
	int itemId;
	@Test
	public void fetchAllProducts() throws IOException {
		Response res=given()
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.auth().oauth2(jwtToken)
		.baseUri("https://www.shoppersstack.com/shopping")
		.when().get("/products/alpha");
		
        List<Integer> productIds = res.jsonPath().getList("data.productId");
        
        productId = productIds.get(1);
        System.out.println("product id is: "+productId);
        quantity = res.jsonPath().getInt("data[0].quantity");  
        System.out.println("quantity is: "+quantity);
        
        res.then().assertThat().statusCode(200);
        
        FileWriter file = new FileWriter("response.json");
        file.write(res.asPrettyString());
        file.close();
        
        System.out.println("this is second commit");
        System.out.println("hiiii");
		System.out.println("commiting in branch1");
	}
	
}
