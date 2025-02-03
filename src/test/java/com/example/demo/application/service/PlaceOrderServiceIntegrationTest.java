package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.adapter.client.inventory.DeductInventoryAdapter;
import com.example.demo.adapter.persistence.order.adapter.OrderPersistenceAdapter;
import com.example.demo.application.service.PlaceOrderService.PlaceOrderCommand;
import jakarta.validation.ConstraintViolationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
class PlaceOrderServiceIntegrationTest {

    @Autowired
    PlaceOrderService placeOrderService;

    @MockitoBean
    OrderPersistenceAdapter orderPersistenceAdapter;

    @MockitoBean
    DeductInventoryAdapter deductInventoryAdapter;

    @MockitoBean
    TransactionTemplate transactionTemplate;

    @Test
    void place_order_should_create_and_save_order() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id-1", "product-id-1", 1, new BigDecimal("100.0"));

        placeOrderService.placeOrder(command);
    }

    @Test
    void place_order_should_throw_exception_when_price_is_null() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id-1", "product-id-1", 1, null);

        assertThatThrownBy(() -> placeOrderService.placeOrder(command))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
