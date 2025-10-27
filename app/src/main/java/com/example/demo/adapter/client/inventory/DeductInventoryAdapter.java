package com.example.demo.adapter.client.inventory;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

public class DeductInventoryAdapter {
    private final RestClient restClient;

    public DeductInventoryAdapter(RestClient.Builder restClientBuilder, String baseUrl) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    public void deductInventory(String productId, int quantity) {
        restClient
                .post()
                .uri("/deduct-inventory")
                .body(new DeductInventoryRequest(productId, quantity))
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.isSameCodeAs(HttpStatus.CONFLICT), (request, response) -> {
                    throw new DeductInventoryFailedException();
                })
                .toBodilessEntity();
    }

    private record DeductInventoryRequest(String productId, int quantity) {}
}
