package com.example.demo.adapter.web.order.adapter;

import com.example.demo.adapter.web.order.PlaceOrderController;
import com.example.demo.application.service.PlaceOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Component
public class PlaceOrderAdapter {
    private final PlaceOrderService placeOrderService;

    public void placeOrder(
            @RequestBody @Valid PlaceOrderController.PlaceOrderRequest request,
            @AuthenticationPrincipal UserDetails user) {
        PlaceOrderService.PlaceOrderCommand command = new PlaceOrderService.PlaceOrderCommand(
                user.getUsername(), request.productId(), request.quantity(), request.price());
        placeOrderService.placeOrder(command);
    }
}
