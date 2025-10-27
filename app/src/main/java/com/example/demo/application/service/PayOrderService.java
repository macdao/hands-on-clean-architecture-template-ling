package com.example.demo.application.service;

import com.example.demo.adapter.persistence.order.adapter.OrderPersistenceAdapter;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayOrderService {
    private final OrderPersistenceAdapter orderPersistenceAdapter;

    @Transactional
    public void payOrder(String orderId) throws OrderNotFoundException {
        OrderId orderIdObj = new OrderId(orderId);
        Order order = orderPersistenceAdapter
                .findById(orderIdObj)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found"));

        order.pay();

        orderPersistenceAdapter.save(order);
    }
}
