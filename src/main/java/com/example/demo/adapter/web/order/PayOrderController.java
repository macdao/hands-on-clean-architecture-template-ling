package com.example.demo.adapter.web.order;

import com.example.demo.application.service.PayOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PayOrderController {
    private final PayOrderService payOrderService;

    @PostMapping("/orders/{orderId}/pay")
    public void payOrder(@PathVariable String orderId) {
        payOrderService.payOrder(orderId);
    }
}
