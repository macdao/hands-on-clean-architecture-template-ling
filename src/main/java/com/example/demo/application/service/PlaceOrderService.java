package com.example.demo.application.service;

import com.example.demo.application.port.out.DeductInventoryPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class PlaceOrderService {
    private final SaveOrderPort saveOrderPort;
    private final DeductInventoryPort deductInventoryPort;
    private final TransactionTemplate transactionTemplate;

    public void placeOrder(@Valid PlaceOrderCommand command) {
        deductInventoryPort.deductInventory(command.productId(), command.quantity());

        transactionTemplate.execute(status -> {
            Order order = new Order(
                    new UserId(command.buyerId()),
                    new ProductId(command.productId()),
                    command.quantity(),
                    command.price());

            saveOrderPort.save(order);
            return null;
        });
    }

    public record PlaceOrderCommand(
            @NotNull String buyerId,
            @NotNull String productId,
            @NotNull @Min(1) Integer quantity,
            @NotNull BigDecimal price) {}
}
