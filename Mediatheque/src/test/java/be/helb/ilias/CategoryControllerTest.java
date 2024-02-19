package be.helb.ilias;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.model.Category;
import org.example.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;


public class CategoryControllerTest {

    @Test
    public void testListCategories() {
        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/category/list")
                .then().statusCode(200)
                .body("data", not(empty()));
    }

    @Test
    public void testAddCategory() {
        Category category = new Category();
        category.setName("New Category");

        RestAssured.with()
                .body(category).contentType(ContentType.JSON)
                .when().request("POST", "/category/save")
                .then().statusCode(200)
                .body("name", equalTo(category.getName())); // Mise à jour de l'assertion
    }



    @Test
    public void testDeleteCategory() {
        Long categoryId = 5L; // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("DELETE", "/category/delete/" + categoryId)
                .then().statusCode(200); // Pas de body à vérifier après la suppression
    }


    @Test
    public void testGetCategoryById() {
        Long categoryId = 3L; // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/category/getBy/" + categoryId)
                .then().statusCode(200)
                .body("id", equalTo(categoryId.intValue())); // Mise à jour de l'assertion
    }
}
