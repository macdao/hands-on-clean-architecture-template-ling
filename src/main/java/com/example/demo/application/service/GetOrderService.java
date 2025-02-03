package com.example.demo.application.service;

import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderService {
    private final FindOrderPort findOrderPort;

    public Order findById(String orderId) throws OrderNotFoundException {
        return findOrderPort
                .findById(new OrderId(orderId))
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }
}
