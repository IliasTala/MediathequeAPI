package be.helb.ilias;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class ProductControllerTest {

    @Test
    public void testAddProduct() {
        Product product = new Product(null, "Produit1", 10.00);

        RestAssured.with()
                .body(product).contentType(ContentType.JSON)
                .when().request("POST", "/products/save")
                .then().statusCode(200)
                .body("name", equalTo(product.getName())); // Màj de l'assertion
    }


    @Test
    public void testListProducts() {
        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/products/list")
                .then().statusCode(200)
                .body("data", not(empty()));
    }


    @Test
    public void testDeleteProduct() {
        Long productIdToDelete = 5L;  // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("DELETE", "/products/delete/" + productIdToDelete)
                .then().statusCode(200);
    }


    @Test
    public void testGetProductById() {
        Long productIdToGet = 8L;  // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/products/getBy/" + productIdToGet)
                .then().statusCode(200)
                .body("id", equalTo(productIdToGet.intValue())); // Màj de l'assertion
    }
}
