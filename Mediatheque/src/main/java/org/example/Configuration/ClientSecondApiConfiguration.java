package org.example.Configuration;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callSecondApi")
public class ClientSecondApiConfiguration {

    private final OkHttpClient client = new OkHttpClient();

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProducts() {
        return executeRequest("http://localhost:8081/products");
    }

    @GetMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProductsByAvailabilityAndById(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Boolean available) {
        String url = "http://localhost:8081/products/search?id=" + id + "&available=" + available;
        return executeRequest(url);
    }

    private ResponseEntity<String> executeRequest(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                final String responseData = response.body().string();
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Request to external API failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while communicating with the external API", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
