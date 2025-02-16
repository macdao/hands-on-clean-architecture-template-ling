package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
class PayOrderServiceTest {

    @Mock
    OrderPersistenceAdapter orderPersistenceAdapter;

    @InjectMocks
    PayOrderService payOrderService;

    @Test
    void pay_order_should_pay_and_save_order_when_order_exists() throws OrderNotFoundException {
        String orderId = "order-id-1";
        Order mockOrder = mock(Order.class);
        when(orderPersistenceAdapter.findById(new OrderId(orderId))).thenReturn(Optional.of(mockOrder));

        payOrderService.payOrder(orderId);

        verify(mockOrder).pay();
        verify(orderPersistenceAdapter).save(mockOrder);
    }

    @Test
    void pay_order_should_throw_order_not_found_exception_when_order_does_not_exist() {
        String orderId = "order-id-1";
        when(orderPersistenceAdapter.findById(new OrderId(orderId))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> payOrderService.payOrder(orderId)).isInstanceOf(OrderNotFoundException.class);
        verify(orderPersistenceAdapter, never()).save(any());
    }
}
