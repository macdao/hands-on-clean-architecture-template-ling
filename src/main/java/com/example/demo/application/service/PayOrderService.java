package com.example.demo.application.service;

import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayOrderService {
    private final FindOrderPort findOrderPort;
    private final SaveOrderPort saveOrderPort;

    @Transactional
    public void payOrder(String orderId) throws OrderNotFoundException {
        OrderId orderIdObj = new OrderId(orderId);
        Order order = findOrderPort
                .findById(orderIdObj)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found"));

        order.pay();

        saveOrderPort.save(order);
    }
}
