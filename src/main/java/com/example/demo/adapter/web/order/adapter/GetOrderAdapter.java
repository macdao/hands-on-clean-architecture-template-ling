package com.example.demo.adapter.web.order.adapter;

import com.example.demo.adapter.web.order.GetOrderController;
import com.example.demo.application.service.GetOrderService;
import com.example.demo.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetOrderAdapter {
    private final GetOrderService getOrderService;

    public GetOrderController.GetOrderResponse getOrder(String orderId) {
        Order order = getOrderService.findById(orderId);
        return new GetOrderController.GetOrderResponse(
                order.getId().value(),
                order.getBuyerId().value(),
                order.getProductId().value(),
                order.getQuantity(),
                order.getStatus().name(),
                order.getPrice(),
                order.getCreatedDate(),
                order.getLastModifiedDate());
    }
}
