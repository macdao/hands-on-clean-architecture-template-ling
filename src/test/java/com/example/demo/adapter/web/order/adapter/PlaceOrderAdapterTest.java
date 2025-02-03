package com.example.demo.adapter.web.order.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;

import com.example.demo.adapter.web.order.PlaceOrderController;
import com.example.demo.application.service.PlaceOrderService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

@ExtendWith(MockitoExtension.class)
class PlaceOrderAdapterTest {
    @Mock
    private PlaceOrderService placeOrderService;

    @InjectMocks
    private PlaceOrderAdapter placeOrderAdapter;

    @Test
    void place_order_should_call_use_case() {
        placeOrderAdapter.placeOrder(
                new PlaceOrderController.PlaceOrderRequest("product-id-1", 10, new BigDecimal("200.0")),
                User.withUsername("user1").password("").build());

        verify(placeOrderService).placeOrder(assertArg(command -> {
            assertThat(command)
                    .returns("user1", from(PlaceOrderService.PlaceOrderCommand::buyerId))
                    .returns("product-id-1", from(PlaceOrderService.PlaceOrderCommand::productId))
                    .returns(10, from(PlaceOrderService.PlaceOrderCommand::quantity))
                    .returns(new BigDecimal("200.0"), from(PlaceOrderService.PlaceOrderCommand::price));
        }));
    }
}
