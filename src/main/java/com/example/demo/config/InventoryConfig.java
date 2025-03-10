package com.example.demo.config;

import com.example.demo.adapter.client.inventory.DeductInventoryAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class InventoryConfig {
    @Bean
    DeductInventoryAdapter inventoryAdapter(
            RestClient.Builder restClientBuilder, @Value("${example.inventory.base-url}") String baseUrl) {
        return new DeductInventoryAdapter(restClientBuilder, baseUrl);
    }
}
