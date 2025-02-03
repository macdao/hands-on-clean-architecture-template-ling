package com.example.demo.adapter.client.inventory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@SpringBootTest
@AutoConfigureStubRunner(ids = "client:inventory", generateStubs = true)
class InventoryAdapterTest {

    @Autowired
    RestClient.Builder restClientBuilder;

    private DeductInventoryAdapter deductInventoryAdapter;

    @StubRunnerPort("inventory")
    int port;

    @BeforeEach
    void setUp() {
        deductInventoryAdapter = new DeductInventoryAdapter(restClientBuilder, "http://localhost:" + port);
    }

    @Test
    void deduct_inventory_should_success() {
        deductInventoryAdapter.deductInventory("product-id-1", 5);
    }

    @Test
    void deduct_inventory_should_conflict() {
        assertThatThrownBy(() -> deductInventoryAdapter.deductInventory("product-id-2", 5))
                .isInstanceOf(DeductInventoryFailedException.class);
    }

    @Test
    void deduct_inventory_should_post_failed() {
        assertThatThrownBy(() -> deductInventoryAdapter.deductInventory("product-id-999", 5))
                .isInstanceOf(RestClientException.class);
    }
}
