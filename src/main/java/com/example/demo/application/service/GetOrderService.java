package com.example.demo.application.service;

import com.example.demo.adapter.persistence.order.adapter.OrderPersistenceAdapter;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderService {
    private final OrderPersistenceAdapter orderPersistenceAdapter;

    public Order findById(String orderId) throws OrderNotFoundException {
        return orderPersistenceAdapter
                .findById(new OrderId(orderId))
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }
}
