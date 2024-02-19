package be.helb.ilias;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.*;


public class UserControllerTest {


    @Test
    public void testListUsers() {
        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/users/list")
                .then().statusCode(200)
                .body("data", not(empty()));
    }



    @Test
    public void testAddUser() {
        User user = new User(null, "John", "Doe", 30, null);

        RestAssured.with()
                .body(user).contentType(ContentType.JSON)
                .when().request("POST", "/users/save")
                .then().statusCode(200)
                .body("firstName", equalTo(user.getFirstName()))
                .body("lastName", equalTo(user.getLastName()));
    }


    @Test
    public void testDeleteUser() {
        Long userIdToDelete = 5L;  // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("DELETE", "/users/delete/" + userIdToDelete)
                .then().statusCode(200);
    }

    @Test
    public void testGetUserById() {
        Long userIdToGet = 6L;  // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/users/getBy/" + userIdToGet)
                .then().statusCode(200)
                .body("id", equalTo(userIdToGet.intValue()));
    }
//    @Test
//    public void testUpdateUser() {
//        User userToUpdate = new User(1L, "Jane", "Doe", 32, null);
//        RestAssured.with()
//                .body(userToUpdate).contentType(ContentType.JSON)
//                .when().request("PUT", "/users/update")
//                .then().statusCode(200)
//                .body("data.firstName", equalTo(userToUpdate.getFirstName()))
//                .body("data.lastName", equalTo(userToUpdate.getLastName()));
//    }
}
