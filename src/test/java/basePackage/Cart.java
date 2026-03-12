package basePackage;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class Cart extends baseClass {

	int productId;
	int quantity;
	int itemId;
	@Test
	public void fetchAllProducts() {
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
	}

	@Test(dependsOnMethods = "fetchAllProducts")
	public void addProduct() {
		
		AddProduct add = new AddProduct(productId, quantity);
		        Response res = given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)                                            
				.pathParam("shopperId", shopperId)
				.body(add)
				.when().post("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts");
		         
		        itemId= res.jsonPath().get("data.itemId");
		       res .then()
		 		.assertThat().statusCode(201)
		 		.log().all();
				
		
	}
	
	@Test(dependsOnMethods = "addProduct")
	public void updateProduct() {
		UpdateProduct update = new UpdateProduct(productId, 2);
		
		Response res = given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.pathParam("shopperId", shopperId)
				.pathParam("itemId", itemId)
				.body(update)
				.when().put("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts/{itemId}");
		
		        res .then()
 		        .assertThat().statusCode(200)
 		        .log().all();
	}
	
	@Test(dependsOnMethods = "updateProduct")
	public void deleteProduct() {
		DeleteProduct delete = new DeleteProduct(productId, quantity);
		
		Response res = given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.pathParam("shopperId", shopperId)
				.pathParam("productId", productId)
				.body(delete)
				.when().delete("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts/{productId}");
		
		        res .then()
 		        .assertThat().statusCode(200)
 		        .log().all();
	
	}

}
