package be.helb.ilias;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.Matchers.*;

public class OrderControllerTest {

    @Test
    public void testAddOrder() {
        Order order = new Order();

        RestAssured.with()
                .body(order).contentType(ContentType.JSON)
                .when().request("POST", "/orders/save")
                .then().statusCode(200)
                .body("orderNumber", notNullValue()); // Mise à jour de l'assertion
    }

    @Test
    public void testListOrders() {
        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/orders/list")
                .then().statusCode(200)
                .body("data", not(empty()));
    }



    @Test
    public void testDeleteOrder() {
        Long orderIdToDelete = 6L; // tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("DELETE", "/orders/delete/" + orderIdToDelete)
                .then().statusCode(200);
    }

    @Test
    public void testGetOrderById() {
        Long orderIdToGet = 4L;// tester avec l'id existant

        RestAssured.with()
                .contentType(ContentType.JSON)
                .when().request("GET", "/orders/getBy/" + orderIdToGet)
                .then().statusCode(200)
                .body("orderNumber", equalTo(orderIdToGet.intValue())); // Mise à jour de l'assertion
    }

//    @Test
//    public void testUpdateOrder() {
//        Order orderToUpdate = new Order();
//        orderToUpdate.setOrderNumber(2L); // Assurez-vous que cet ID existe déjà dans la base de données
//        orderToUpdate.setTotalPrice(54.97);
//        orderToUpdate.setOrderTime(LocalDateTime.parse("2023-12-03T12:00:00"));
//        orderToUpdate.setDeliveryStatus("En cours");
//
//        RestAssured.with()
//                .body(orderToUpdate).contentType(ContentType.JSON)
//                .when().request("PUT", "/orders/update")
//                .then().statusCode(200)
//                .body("message", equalTo("Successfully updated order within the database"));
//    }
}
