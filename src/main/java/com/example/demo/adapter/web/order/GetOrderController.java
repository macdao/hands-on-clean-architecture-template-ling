package com.example.demo.adapter.web.order;

import com.example.demo.adapter.web.order.adapter.GetOrderAdapter;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GetOrderController {
    private final GetOrderAdapter getOrderAdapter;

    @GetMapping("/orders/{orderId}")
    public GetOrderResponse getOrder(@PathVariable String orderId) {
        return getOrderAdapter.getOrder(orderId);
    }

    public record GetOrderResponse(
            String id,
            String buyerId,
            String productId,
            int quantity,
            String status,
            BigDecimal price,
            Instant createdDate,
            Instant lastModifiedDate) {}
}
