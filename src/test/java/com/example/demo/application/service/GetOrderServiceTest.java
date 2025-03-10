package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.adapter.persistence.order.adapter.OrderPersistenceAdapter;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderServiceTest {

    @Mock
    OrderPersistenceAdapter orderPersistenceAdapter;

    @InjectMocks
    GetOrderService getOrderService;

    @Test
    void find_by_id_should_return_order_when_order_exists() throws OrderNotFoundException {
        String orderId = "order-id-1";
        Order mockOrder = mock(Order.class);
        when(orderPersistenceAdapter.findById(new OrderId(orderId))).thenReturn(Optional.of(mockOrder));

        Order result = getOrderService.findById(orderId);

        assertThat(result).isSameAs(mockOrder);
    }

    @Test
    void find_by_id_should_throw_order_not_found_exception_when_order_does_not_exist() {
        String orderId = "order-id-1";
        when(orderPersistenceAdapter.findById(new OrderId(orderId))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getOrderService.findById(orderId))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Order not found with id: " + orderId);
    }
}
